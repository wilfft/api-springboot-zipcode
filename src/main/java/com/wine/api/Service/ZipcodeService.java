package com.wine.api.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
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

    public List<ZipcodeEntity> allStores() {
	List<ZipcodeEntity> zipcodesOrd = zip.findByOrderByFaixaInicioAsc();

	return zipcodesOrd;
    }

    public String locateStoreByZipCode(Integer zipcodeNumber) {
	List<ZipcodeEntity> zips = this.allStores();
	for (int i = 0; i < zips.size(); i++) {
	    if (zipcodeNumber >= zips.get(i).getFaixaInicio() && zipcodeNumber <= zips.get(i).getFaixaFim()) {
		String CodLoja = zips.get(i).getCodigoDaLoja().toString();
		return CodLoja;
	    }
	}
	return "Loja não Localizada";
    }

    @Transactional
    public String save(@RequestBody @Valid ZipcodeEntity zipcode) {
	ZipcodeEntity z = zip.save(zipcode);
	return "Cep adicionado com sucesso \n" + "\nCOD LOJA: " + z.getCodigoDaLoja() + "\nFAIXA INICIO: "
		+ z.getFaixaInicio() + "\nFAIXA INICIO: " + z.getFaixaFim();
    }

    public String AllowOrNot(@Valid Integer inicio, Integer fim) {
	String conflito = "";
	List<ZipcodeEntity> zips = this.allStores();
	if (zips.size() == 0) {
	    return "valid";
	}
	for (int i = 0; i < zips.size(); i++) {
	    if (inicio > zips.get(i).getFaixaInicio() && fim > zips.get(i).getFaixaFim()) {
		conflito = "Conflito com a loja " + zips.get(i).getCodigoDaLoja() + " " + zips.get(i).getFaixaInicio()
			+ " " + zips.get(i).getFaixaFim();
		if (i == zips.size() - 1 && inicio > zips.get(i).getFaixaFim() && fim <= 99999999) {
		    // System.out.println("FASE 4");
		    return "valid";
		}
		continue;
	    } else {
		if (fim < zips.get(i).getFaixaFim()) {
		    return "valid";
		} else
		    conflito = "Conflito com a loja " + zips.get(i).getCodigoDaLoja() + " "
			    + zips.get(i).getFaixaInicio() + " " + zips.get(i).getFaixaFim();

		return conflito;

	    }
	}

	return conflito;
    }

    public Optional<ZipcodeEntity> getZipById(Long id) {
	return zip.findById(id);
    }

    @Transactional
    public void remove(Long id) {
	zip.deleteById(id);
    }

    public String allowUpdate(ZipcodeEntity zipcode, Long id) {
	String conflito = "";
	Integer inicio = zipcode.getFaixaInicio();
	Integer fim = zipcode.getFaixaFim();

	List<ZipcodeEntity> zips = this.allStores();
	zips.removeIf(e -> e.getId() == id);
	for (int i = 0; i < zips.size(); i++) {
	    // System.out.println(inicio + ">>>" + zips.get(i).getFaixaInicio());
	    // System.out.println(fim + ">>>" + zips.get(i).getFaixaFim());
	    if (inicio > zips.get(i).getFaixaInicio() && fim > zips.get(i).getFaixaFim()) {
		conflito = "Conflito com a loja " + zips.get(i).getCodigoDaLoja() + " " + zips.get(i).getFaixaInicio()
			+ " " + zips.get(i).getFaixaFim();
		// System.out.println(inicio + " >>> " + zips.get(i).getFaixaInicio() + " && " +
		// fim + " >>> "
		// + zips.get(i).getFaixaFim());
		if (i == zips.size() - 1 && inicio > zips.get(i).getFaixaFim() && fim <= 99999999) {
		    // System.out.println("FASE 4");
		    return "valid";
		}
		continue;
	    } else {
		if (fim < zips.get(i).getFaixaFim() && fim < zips.get(i).getFaixaInicio() + 1) {
		    // System.out.println(fim + "FASE 5 " + zips.get(i).getFaixaFim());
		    return "valid";
		} else

		    conflito = "Conflito com a loja " + zips.get(i).getCodigoDaLoja() + " "
			    + zips.get(i).getFaixaInicio() + " " + zips.get(i).getFaixaFim();
		// System.out.println(conflito);
		return conflito;

	    }
	}

	return conflito;

    }

    @Transactional
    public String update(Long id, ZipcodeEntity zipcode) {
	Optional<ZipcodeEntity> optional = zip.findById(id);
	String falha;
	if (optional.isPresent()) {
	    ZipcodeEntity db = optional.get();

	    if (this.allowUpdate(zipcode, id) == "valid") {
		// System.out.println(" " + zipcode.getFaixaInicio() + " " +
		// zipcode.getFaixaFim() + " " + id);
		db.setCodigoDaLoja(zipcode.getCodigoDaLoja());
		db.setFaixaInicio(zipcode.getFaixaInicio());
		db.setFaixaFim(zipcode.getFaixaFim());
		zip.save(db);
		return "Atualizado";
	    }
	}
	return "Não foi possivel alterar, verifique os dados";
    }

}