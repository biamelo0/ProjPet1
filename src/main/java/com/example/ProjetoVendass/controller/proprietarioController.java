package com.example.ProjetoVendass.controller;

import java.util.List; 

  

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

import org.springframework.web.bind.annotation.RestController; 

  

import com.example.AjusteAvalia.entities.Proprietario; 

import com.example.AjusteAvalia.service.ProprietarioService; 

  

import jakarta.validation.Valid; 

@RestController 
@RequestMapping("/proprietario") 
public class ProprietarioController { 

    private final ProprietarioService proprietarioService; 

    @Autowired 
    public ProprietarioController(ProprietarioService proprietarioService) { 
        this.proprietarioService = proprietarioService; 
    } 
    
    //Query Method 
    @GetMapping("/cidade/{cidade}") 
    public ResponseEntity<List<Proprietario>> buscarProprietariosPorCidade(@PathVariable String cidade) { 
      List<Proprietario> proprietarios = proprietarioService.buscarProprietarioPorCidade(cidade); 
      return ResponseEntity.ok(proprietarios); 
    } 

  //Query Method 
    @GetMapping("/nome/{nome}") 
    public ResponseEntity<List<Veterinario>> buscarVeterinariosPorNome(@PathVariable String nome) { 
      List<Veterinario> veterinarios = veterinarioService.buscarVeterinarioPorNome(nome); 
      return ResponseEntity.ok(veterinarios); 
    } 
    
  //Query Method 
    @GetMapping("/nomeCompleto/{nomeCompleto}") 
    public ResponseEntity<List<Veterinario>> buscarVeterinariosPorNomeCompleto(@PathVariable String nomeCompleto) { 
      List<Veterinario> veterinarios = veterinarioService.buscarVeterinarioPorNomeCompleto(nomeCompleto); 
      return ResponseEntity.ok(veterinarios); 
    }

    @GetMapping("/{id}") 
    public ResponseEntity<Proprietario> getProprietarioById(@PathVariable Long id) { 
        Proprietario proprietario = proprietarioService.getProprietarioById(id); 
        if (proprietario != null) { 
            return ResponseEntity.ok(proprietario); 
        } else { 
            return ResponseEntity.notFound().build(); 
        } 
    } 

    public ResponseEntity<List<Proprietario>> getAllProprietarios() { 
        List<Proprietario> proprietarios = proprietarioService.getAllProprietario(); 
        return ResponseEntity.ok(proprietarios); 
    } 

    @PostMapping("/") 
    public ResponseEntity<Proprietario> criarProprietario(@RequestBody @Valid Proprietario proprietario) { 
        Proprietario criarProprietario = proprietarioService.salvarProprietario(proprietario); 
        return ResponseEntity.status(HttpStatus.CREATED).body(criarProprietario); 
    } 

    @PutMapping("/{id}") 
    public ResponseEntity<Proprietario> updateProprietario(@PathVariable Long id, @RequestBody @Valid Proprietario proprietario) { 
        Proprietario updatedProprietario = proprietarioService.updateProprietario(id, proprietario); 
        if (updatedProprietario != null) { 
            return ResponseEntity.ok(proprietario); 
        } else { 
            return ResponseEntity.notFound().build(); 
        } 
    } 

    @DeleteMapping("/{id}") 
    public ResponseEntity<Proprietario> deleteProprietario(@PathVariable Long id) { 
        boolean deleted = proprietarioService.deleteProprietario(id); 
        if (deleted) { 
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  
        } else { 
            return ResponseEntity.notFound().build(); 
        } 
    } 
} 