package com.wine.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wine.api.Entity.ZipcodeEntity;
import com.wine.api.Service.ValidatorService;
import com.wine.api.Service.ZipcodeService;

@RestController
@RequestMapping("/zipcode")
public class ApiController {

    @Autowired
    private ZipcodeService zipCodeService;
    @Autowired
    private ValidatorService validatorService;

    @PostMapping("/new")
    public String addZipCode(@RequestBody ZipcodeEntity zipcode) {

	if (validatorService.ValidateZipcode(zipcode.getFaixaInicio(), zipcode.getFaixaFim())) {

	    if (zipCodeService.AllowOrNot(zipcode.getFaixaInicio(), zipcode.getFaixaFim())) {
		System.out.println("ADDICIONADO");
		zipCodeService.save(zipcode);
		return "Adiconado com sucesso";
	    }
	} else {
	    return "Corrija os dados";
	}
	return null;
    }

    @GetMapping("/search")
    public String stores(@RequestBody Integer zipcode) {

	System.out.println("++++++++++++  " + zipCodeService.locateStoreByZipCode(zipcode));
	return null;

    }
    /*-  @GetMapping("/get")
    public List<ZipcodeEntity> retorna(Integer inicio, Integer fim) {
    if (fim > inicio) {
        return service.allZips(inicio, fim);
    } else
        return null;
    }*/
}
