package com.ats.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ats.entity.Backup;
import com.ats.entity.Contractor;
import com.ats.entity.Legal;
import com.ats.entity.Phase2;
import com.ats.entity.Phase3;
import com.ats.entity.Resgistration;
import com.ats.entity.Tender;
import com.ats.entity.VerifiedTender;
import com.ats.request.BackupRequest;
import com.ats.request.ContractorRequest;
import com.ats.request.Phase2Request;
import com.ats.request.Registration;
import com.ats.request.TenderRequest;
import com.ats.request.VerifiedTenderRequest;
import com.ats.service.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.MessagingException;

@Controller
@CrossOrigin(origins="https://tenderdedo.vercel.app/")
public class RestController {
@Autowired
RegistrationService service;
@Autowired
private ObjectMapper objm;
@GetMapping(path = "/start")
@ResponseBody
public String getStarted() {
	return "Project Started";
}
@RequestMapping(path="/createuser", method = RequestMethod.POST )
public ResponseEntity<String> CreateUser(@RequestBody Registration request) {
	String response = service.Create(request);
	return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
@RequestMapping(path="/check/{name}/{password}",method = RequestMethod.GET)
public ResponseEntity<String> checkName(@PathVariable String name, @PathVariable String password){
	boolean b = service.Createname(name,password);
	if(b) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("found Sucessfully");
	}
	else {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("not Found ");
	}
}
@RequestMapping(path ="/getregdetail",method =  RequestMethod.GET)
@ResponseBody
public List<Resgistration> getregdetail(){
	return service.getreg();
}
 @RequestMapping(path = "/createtender", method = RequestMethod.POST)
 public ResponseEntity<String> Createtender(@RequestParam("file") MultipartFile file , @RequestParam String Data) {
	try {
		TenderRequest tr = objm.readValue(Data,TenderRequest.class);
		String Response = service.CreateTender(tr, file);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");
	}	
  }
@RequestMapping(path ="/gettender",method =  RequestMethod.GET)
@ResponseBody
public List<Tender> getall(){
	return service.gettender();
}
@RequestMapping(path="/deletetender/{id}" , method = RequestMethod.DELETE)
public ResponseEntity<String> deletetender(@PathVariable int id){
	String response = service.DeleteTenderform(id);
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
}
@RequestMapping(path="/createverifiedtender", method = RequestMethod.POST )
public ResponseEntity<String> CreateUser(@RequestBody VerifiedTenderRequest request) {
	String response = service.CreateVerifiedtender(request);
	return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
@RequestMapping(path ="/getverifiedtender",method =  RequestMethod.GET)
@ResponseBody
public List<VerifiedTender> getverfiedtender(){
	return service.GetverifiedTender();
}
@RequestMapping(path = "/createContractor", method = RequestMethod.POST)
public ResponseEntity<String> CreateContractor(@RequestParam("file") MultipartFile file , @RequestParam String Data) {
	try {
		ContractorRequest tr = objm.readValue(Data,ContractorRequest.class);
		String Response = service.Contracotorrequest(tr, file);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");
	}	
 }
@RequestMapping(path ="/getcontractor",method =  RequestMethod.GET)
@ResponseBody
public List<Contractor> getcontractor(){
	return service.getContractor();
}
@RequestMapping(path ="/getcontractor/{id}",method =  RequestMethod.GET)
@ResponseBody
public List<Contractor> getcontractor(@PathVariable int id){
	return service.getContractorByTenderId(id);
}
@RequestMapping(path="/deletecontractor/{id}" , method = RequestMethod.DELETE)
public ResponseEntity<String> deleteContractor(@PathVariable int id){
	String response = service.DeleteContractor(id);
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
}
@RequestMapping(path = "getverifiedTenderByid/{id}",method = RequestMethod.GET)
@ResponseBody
public Optional<VerifiedTender> getVerifiedTenderById(@PathVariable int id){
	return  service.getTenderbyid(id);
}
@RequestMapping(path="/deletephase2/{id}" , method = RequestMethod.DELETE)
public ResponseEntity<String> deletephase2(@PathVariable int id){
	String response = service.Deletephase2(id);
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
}
@RequestMapping(path="/phase2", method = RequestMethod.POST )
public ResponseEntity<String> phase2create(@RequestBody Phase2Request request) {
	String response = service.Createphase2tender(request);
	return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
@RequestMapping(path ="/getphase2",method =  RequestMethod.GET)
@ResponseBody
public List<Phase2> getphase2(){
	return service.Getphase2tenderrequest();
}
@RequestMapping(path ="/getphase2byid/{id}",method =  RequestMethod.GET)
@ResponseBody
public List<Phase2> getphase2byid(@PathVariable int id){
	return service.Getphase2tenderbyid(id);
}
@RequestMapping(path="/deletephase3/{id}" , method = RequestMethod.DELETE)
public ResponseEntity<String> deletephase3(@PathVariable int id){
	String response = service.Deletephase3(id);
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
}
@RequestMapping(path="/phase3", method = RequestMethod.POST )
public ResponseEntity<String> phase3create(@RequestBody Phase2Request request) {
	String response = service.Createphase3tender(request);
	return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
@RequestMapping(path ="/getphase3",method =  RequestMethod.GET)
@ResponseBody
public List<Phase3> getphase3(){
	return service.Getphase3tenderrequest();
}
@RequestMapping(path ="/getphase3byid/{id}",method =  RequestMethod.GET)
@ResponseBody
public List<Phase3> getphase3byid(@PathVariable int id){
	return service.Getphase3tenderbyid(id);
}
@RequestMapping(path="/legal", method = RequestMethod.POST )
public ResponseEntity<String> legalcreate(@RequestBody Phase2Request request) {
	String response = service.Createlegal(request);
	return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
@RequestMapping(path ="/getlegal",method =  RequestMethod.GET)
@ResponseBody
public List<Legal> getlegalall(){
	return service.Getlegalalltender();
}
@RequestMapping(path ="/getlegalbyid/{id}",method =  RequestMethod.GET)
@ResponseBody
public List<Legal> getlegalbyid(@PathVariable int id){
	return service.Getlegaltenderbyid11(id);
}
@RequestMapping(path ="/getlegalbyContractor/{id}",method =  RequestMethod.GET)
@ResponseBody
public Optional<Legal> getlegalbycontractor(@PathVariable int id){
	return service.getlegalbyid(id);
}

@RequestMapping(path="/deletelegal/{id}" , method = RequestMethod.DELETE)
public ResponseEntity<String> deletelegal(@PathVariable int id){
	String response = service.Deletelegal(id);
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
}
@PostMapping("/send")
public ResponseEntity<String> sendemail(@RequestParam String id, @RequestParam String body) {
    try {
        String response = service.sendemail(id, body);
        return ResponseEntity.ok(response);
    } catch (MessagingException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
    }
}
@RequestMapping(path="/backup", method = RequestMethod.POST )
public ResponseEntity<String> backupcreate(@RequestBody BackupRequest request) {
	String response = service.createBackup(request);
	return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
@RequestMapping(path ="/getbackup",method =  RequestMethod.GET)
@ResponseBody
public List<Backup> getbackupdata(){
	return service.getBackup();
}
}
