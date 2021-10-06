package it.bank;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import kong.unirest.json.JSONObject;
import okio.BufferedSink;
@RestController
public class Controller {



	@GetMapping(value="/balance/{bankAccount}", produces="application/json")
	public ResponseEntity<String> readBalance(@PathVariable String bankAccount) throws IOException  {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+bankAccount+"/balance")
				.method("GET", null)
				.addHeader("Content-Type", " application/json")
				.addHeader("Auth-Schema", " S2S")
				.addHeader("Api-Key", " FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
				.build();
		Response response = client.newCall(request).execute();
		String resStr = response.body().string();   
		
		return  new ResponseEntity<>(resStr, HttpStatus.OK) ;
	}

	@RequestMapping(value="/bonifico/{bankAccount}/{fiscalCodeBeneficiary}/{description}/{amount}/{date}",produces="application/json")
	public ResponseEntity<String> moneyTransfer(@PathVariable String bankAccount,@PathVariable String fiscalCodeBeneficiary,@PathVariable String description,@PathVariable Number amount,@PathVariable String date) throws IOException, ParseException{

		MoneyTransfer moneyTransfer =new MoneyTransfer();

		Creditor creditor =new Creditor();
		Account account =new Account();

		account.setAccountCode("IT40L0326822311052923800661");

		creditor.setAccount(account);

		creditor.setName("LUCA TERRIBILE");


		moneyTransfer.setAmount(amount);
		moneyTransfer.setCreditor(creditor);
		moneyTransfer.setDescription(description);
		moneyTransfer.setCurrency("EUR");


		moneyTransfer.setExecutionDate(date);
		TaxRelief taxRelief=new TaxRelief();
		taxRelief.setBeneficiaryType("NATURAL_PERSON");
		NaturalPersonBeneficiary nPerson=new NaturalPersonBeneficiary();
		nPerson.setFiscalCode1(fiscalCodeBeneficiary);
		taxRelief.setTaxReliefId("L449");
		taxRelief.setIsCondoUpgrade(false);
		taxRelief.setNaturalPersonBeneficiary(nPerson);
		taxRelief.setCreditorFiscalCode("56258745832");
		moneyTransfer.setTaxRelief(taxRelief);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String a= gson.toJson(moneyTransfer);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+bankAccount+"/payments/money-transfers");


		HttpEntity stringEntity = new StringEntity(a,ContentType.APPLICATION_JSON);
		httpPost.setEntity(stringEntity);
		httpPost.addHeader("Auth-Schema", "S2S");
		httpPost.addHeader("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
		CloseableHttpResponse response2 = httpclient.execute(httpPost);
		
		String json = EntityUtils.toString(response2.getEntity());

		return  new ResponseEntity<>(json, HttpStatus.MULTI_STATUS) ;

	}

	@GetMapping(value="/listTransactions/{bankAccount}/{fromDate}/{toDate}",produces="application/json")
	public ResponseEntity<String> getTransations(@PathVariable String bankAccount,@PathVariable String fromDate,@PathVariable String toDate) throws IOException{

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+bankAccount+"/transactions?fromAccountingDate="+fromDate+"&toAccountingDate="+toDate+"")
				.method("GET", null)
				.addHeader("Content-Type", "application/json")
				.addHeader("Auth-Schema", "S2S")
				.addHeader("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
				.build();
		Response response = client.newCall(request).execute();
		String res =response.body().string();
		return  new ResponseEntity<>(res, HttpStatus.MULTI_STATUS) ;

	}


}
