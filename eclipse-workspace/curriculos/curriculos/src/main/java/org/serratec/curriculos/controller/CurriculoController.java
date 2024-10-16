package org.serratec.curriculos.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.curriculos.dto.CurriculoDto;
import org.serratec.curriculos.service.CurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curriculos")
public class CurriculoController {
	@Autowired
	private CurriculoService servico;

	@GetMapping
	public List<CurriculoDto> obterTodos() {
		return servico.obterTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CurriculoDto> obterPorId(@PathVariable Long id) {
		Optional<CurriculoDto> dto = servico.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CurriculoDto cadastrarCurriculo(@RequestBody CurriculoDto dto) {
		return servico.salvarCurriculo(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaCurriculo(@PathVariable Long id){
		if(!servico.apagarCurriculo(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CurriculoDto> alterarCurriculo(@PathVariable Long id, @RequestBody CurriculoDto dto){
		Optional<CurriculoDto> curriculoAlterado = servico.alterarCurriculo(id, dto);
		if (!curriculoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(curriculoAlterado.get());
	}
}
