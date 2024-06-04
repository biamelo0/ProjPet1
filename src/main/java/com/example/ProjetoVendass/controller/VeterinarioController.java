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

  

import com.example.AjusteAvalia.entities.Veterinario; 

import com.example.AjusteAvalia.service.VeterinarioService; 

  

import jakarta.validation.Valid; 

  

@RestController 
@RequestMapping("/veterinario") 
public class VeterinarioController {
    private final VeterinarioService veterinarioService; 
    
    @Autowired 
    public VeterinarioController(VeterinarioService veterinarioService) { 
        this.veterinarioService = veterinarioService; 
    } 

    //Query Method 
    @GetMapping("/cidade/{cidade}") 
    public ResponseEntity<List<Veterinario>> buscarVeterinariosPorCidade(@PathVariable String cidade) { 
      List<Veterinario> veterinarios = veterinarioService.buscarVeterinarioPorCidade(cidade); 
      return ResponseEntity.ok(veterinarios); 
    } 

  //Query Method 
    @GetMapping("/nome/{nome}") 
    public ResponseEntity<List<Veterinario>> buscarVeterinariosPorNome(@PathVariable String nome) { 
      List<Veterinario> veterinarios = veterinarioService.buscarVeterinarioPorNome(nome); 
      return ResponseEntity.ok(veterinarios); 
    } 
    
  //Query Method 
    @GetMapping("/especialidade/{especialidade}") 
    public ResponseEntity<List<Veterinario>> buscarVeterinariosPorEspecialidade(@PathVariable String especialidade) { 
      List<Veterinario> veterinarios = veterinarioService.buscarVeterinarioPorEspecialidade(especialidade); 
      return ResponseEntity.ok(veterinarios); 
    } 

    @GetMapping("/{id}") 
    public ResponseEntity<Veterinario> getVeterinarioById(@PathVariable Long id) { 
        Veterinario veterinario = veterinarioService.getVeterinarioById(id); 
        if (veterinario != null) { 
            return ResponseEntity.ok(veterinario); 
        } else { 
            return ResponseEntity.notFound().build(); 
        } 
    } 

    public ResponseEntity<List<Veterinario>> getAllVeterinarios() { 
        List<Veterinario> veterinarios = veterinarioService.getAllVeterinario(); 
        return ResponseEntity.ok(veterinarios); 
    } 

    @PostMapping("/") 
    public ResponseEntity<Veterinario> criarVeterinario(@RequestBody @Valid Veterinario veterinario) { 
        Veterinario criarVeterinario = veterinarioService.salvarVeterinario(veterinario); 
        return ResponseEntity.status(HttpStatus.CREATED).body(criarVeterinario); 
    } 

    @PutMapping("/{id}") 
    public ResponseEntity<Veterinario> updateVeterinario(@PathVariable Long id, @RequestBody @Valid Veterinario veterinario) { 
        Veterinario updatedVeterinario = veterinarioService.updateVeterinario(id, veterinario); 
        
        if (updatedVeterinario != null) { 
            return ResponseEntity.ok(veterinario); 
        } else { 
            return ResponseEntity.notFound().build(); 
        } 
    } 

    @DeleteMapping("/{id}") 
    public ResponseEntity<Veterinario> deleteVeterinario(@PathVariable Long id) { 
        boolean deleted = veterinarioService.deleteVeterinario(id); 
        if (deleted) { 
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  
        } else { 
            return ResponseEntity.notFound().build(); 
        } 
    } 
} 