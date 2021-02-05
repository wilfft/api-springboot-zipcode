package com.wine.api.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.wine.api.Entity.ZipcodeEntity;
import com.wine.api.Repository.ZipcodeRepository;

@Service
public class ZipcodeService {

    @Autowired
    private ZipcodeRepository zip;

    public List<ZipcodeEntity> takeAllStores() {
	List<ZipcodeEntity> zipcodesOrd = zip.findByOrderByFaixaInicioAsc();
	System.out.println(zipcodesOrd);
	return zipcodesOrd;
    }

    public String locateStoreByZipCode(Integer zipcodeNumber) {

	List<ZipcodeEntity> zips = this.takeAllStores();

	for (int i = 0; i < zips.size(); i++) {

	    if (zipcodeNumber >= zips.get(i).getFaixaInicio() && zipcodeNumber <= zips.get(i).getFaixaFim()) {

		// return zips.get(i).getCodigoDaLoja();
		return null;
	    }

	}
	System.out.println(zipcodeNumber);
	return "Loja não Localizada";

    }

    public String save(@RequestBody @Valid ZipcodeEntity zipcode) {
	ZipcodeEntity z = zip.save(zipcode);
	return "Zipcode adicionado com sucesso" + "COD LOJA: " + z.getCodigoDaLoja() + " INICIO: " + z.getFaixaInicio()
		+ "/ FIM: " + z.getFaixaFim();
    }

    public boolean AllowOrNot(@Valid Integer inicio, Integer fim) {
	System.out.println("ALOOWNANDO");
	List<ZipcodeEntity> zips = this.takeAllStores();
	for (int i = 0; i < zips.size(); i++) {
	    if (inicio > zips.get(i).getFaixaInicio() && fim > zips.get(i).getFaixaFim()) {
		if (i == zips.size() - 1 && inicio > zips.get(i).getFaixaFim() && fim <= 999999) {
		    return true;
		}
		continue;
	    } else {
		if (fim < zips.get(i).getFaixaFim()) {
		    return true;
		} else
		    break;
	    }
	}
	return false;
    }

}
