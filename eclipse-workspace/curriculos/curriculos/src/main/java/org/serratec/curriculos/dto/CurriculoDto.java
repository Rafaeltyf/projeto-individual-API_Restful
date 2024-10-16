package org.serratec.curriculos.dto;

import java.time.LocalDate;

import org.serratec.curriculos.model.Curriculo;
import org.serratec.curriculos.model.Escolaridade;
import org.serratec.curriculos.model.Status;
import org.serratec.curriculos.model.Vagas;

public record CurriculoDto(
		Long id,
		String candidato,
		LocalDate dataNascimento,
		String cpf,
		Vagas vagas,
		Escolaridade escolaridade,
		Status status
		) {
	
	public Curriculo toEntity() {
		Curriculo curriculo = new Curriculo();
		curriculo.setId(this.id);
		curriculo.setCandidato(candidato);
		curriculo.setDataNascimento(dataNascimento);
		curriculo.setCpf(cpf);
		curriculo.setVagas(vagas);
		curriculo.setEscolaridade(escolaridade);
		curriculo.setStatus(status);
		return curriculo;
	}
	
	public static CurriculoDto toDto(Curriculo curriculo) {
		return new CurriculoDto(curriculo.getId(), curriculo.getCandidato(), 
				curriculo.getDataNascimento(), curriculo.getCpf(), curriculo.getVagas(),
				curriculo.getEscolaridade(), curriculo.getStatus());
	}


	}

