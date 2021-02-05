package com.wine.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wine.api.Entity.ZipcodeEntity;
import com.wine.api.Service.ValidatorService;
import com.wine.api.Service.ZipcodeService;

@RestController
@RequestMapping("/lojas")
public class ApiController {

    @Autowired
    private ZipcodeService zipcodeService;
    @Autowired
    private ValidatorService validatorService;

    @PostMapping("/cep")
    public String newZipcode(@RequestBody ZipcodeEntity zipcode) {
	if (validatorService.ValidateZipcode(zipcode.getFaixaInicio(), zipcode.getFaixaFim())) {
	    if (zipcodeService.AllowOrNot(zipcode.getFaixaInicio(), zipcode.getFaixaFim()) == "valid") {
		String retorno = zipcodeService.save(zipcode);
		return retorno;
	    }
	} else {
	    return "Por favor, corrija os dados";
	}
	return zipcodeService.AllowOrNot(zipcode.getFaixaInicio(), zipcode.getFaixaFim());
    }

    @PatchMapping("/cep/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody ZipcodeEntity zipcode) {
	if (validatorService.ValidateZipcode(zipcode.getFaixaInicio(), zipcode.getFaixaFim())) {
	    return zipcodeService.update(id, zipcode);
	} else {
	    return "Por favor, corrija os dados";
	}

    }

    @DeleteMapping("/cep/{id}")
    public void delete(@PathVariable("id") Long id) {
	zipcodeService.remove(id);
	return;
    }

    @GetMapping("/mais-proxima/{zipcode}")
    public String stores(@PathVariable("zipcode") int zipcode) {
	String codLojaMaisProxima = zipcodeService.locateStoreByZipCode(zipcode);
	return codLojaMaisProxima;
    }

    @GetMapping("/todas-lojas")
    public List<ZipcodeEntity> retorna() {
	List<ZipcodeEntity> todasLojas = zipcodeService.allStores();

	return todasLojas;
    }

}
