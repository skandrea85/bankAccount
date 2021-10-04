package it.bank;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import kong.unirest.json.JSONObject;
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

	@RequestMapping(value="/bonifico/{bankAccount}",produces="application/json")
	public ResponseEntity<String> moneyTransfer(@PathVariable String bankAccount,@PathVariable String fiscaleCode,@PathVariable String amount,@PathVariable String currency ) throws IOException{

		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\r\n  \"creditor\": {\r\n    \"name\": \"LUCA TERRIBILE\",\r\n    \"account\": {\r\n      \"accountCode\": \"IT40L0326822311052923800661\"\r\n      \r\n    },\r\n    \"address\": {\r\n      \"address\": null,\r\n      \"city\": null,\r\n      \"countryCode\": null\r\n    }\r\n  },\r\n  \"executionDate\": \"2019-04-01\",\r\n  \"uri\": \"REMITTANCE_INFORMATION\",\r\n  \"description\": \"Payment invoice 75/2017\",\r\n  \"amount\": 800,\r\n  \"currency\": \"EUR\",\r\n  \"isUrgent\": false,\r\n  \"isInstant\": false,\r\n  \"feeType\": \"SHA\",\r\n  \"feeAccountId\": \"45685475\",\r\n  \"taxRelief\": {\r\n    \"taxReliefId\": \"L449\",\r\n    \"isCondoUpgrade\": false,\r\n    \"creditorFiscalCode\": \"56258745832\",\r\n    \"beneficiaryType\": \"NATURAL_PERSON\",\r\n    \"naturalPersonBeneficiary\": {\r\n      \"fiscalCode1\": \"MRLFNC81L04A859L\",\r\n      \"fiscalCode2\": null,\r\n      \"fiscalCode3\": null,\r\n      \"fiscalCode4\": null,\r\n      \"fiscalCode5\": null\r\n    },\r\n    \"legalPersonBeneficiary\": {\r\n      \"fiscalCode\": null,\r\n      \"legalRepresentativeFiscalCode\": null\r\n    }\r\n  }\r\n}");
		Request request = new Request.Builder()
				.url("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+bankAccount+"/payments/money-transfers ")
				.method("POST", body)
				.addHeader("Auth-Schema", "S2S")
				.addHeader("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
				.addHeader("Content-Type", "application/json")
				.build();
		Response response = client.newCall(request).execute();
		String res =response.body().string();
		return  new ResponseEntity<>(res, HttpStatus.MULTI_STATUS) ;

	}

	@GetMapping(value="/listTransactions/{bankAccount}",produces="application/json")
	public ResponseEntity<String> getTransations(@PathVariable String bankAccount) throws IOException{

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+bankAccount+"/transactions?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01")
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
