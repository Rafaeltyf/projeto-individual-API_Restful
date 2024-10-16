package org.serratec.curriculos.service;

import java.util.List;
import java.util.Optional;
import org.serratec.curriculos.dto.CurriculoDto;
import org.serratec.curriculos.model.Curriculo;
import org.serratec.curriculos.repository.CurriculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurriculoService {
		@Autowired
		private CurriculosRepository repositorio;
		
		public List<CurriculoDto> obterTodos(){
			return repositorio.findAll().stream().map(c -> CurriculoDto.toDto(c)).toList();
		}
		
		public Optional<CurriculoDto> obterPorId(Long id){
			if(!repositorio.existsById(id)) {
				return Optional.empty();
			}
			return Optional.of(CurriculoDto.toDto(repositorio.findById(id).get()));
		}
		
		public CurriculoDto salvarCurriculo(CurriculoDto dto) {
			Curriculo curriculoEntity = repositorio.save(dto.toEntity());
			return CurriculoDto.toDto(curriculoEntity);
		}
		
		public boolean apagarCurriculo(Long id) {
			if(!repositorio.existsById(id)) {
				return false;
			}
			repositorio.deleteById(id);
			return true;
		}

		public Optional<CurriculoDto> alterarCurriculo(Long id, CurriculoDto dto){
			if(!repositorio.existsById(id)) {
				return Optional.empty();
			}
			Curriculo curriculoEntity = dto.toEntity();
			curriculoEntity.setId(id);
			repositorio.save(curriculoEntity);
			return Optional.of(CurriculoDto.toDto(curriculoEntity));
		}
}
