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

  

import com.example.AjusteAvalia.entities.Consulta; 

import com.example.AjusteAvalia.service.ConsultaService; 

  

import jakarta.validation.Valid; 

@RestController 
@RequestMapping("/consulta") 
public class ConsultaController { 

    private final ConsultaService consultaService; 

    @Autowired 
    public ConsultaController(ConsultaService consultaService) { 
        this.consultaService = consultaService; 
    } 

    //Query Method 
    @GetMapping("/data/{data}") 
    public ResponseEntity<List<Consulta>> buscarConsultasPorData(@PathVariable String data) { 
      List<Consulta> consultas = consultaService.buscarConsultaPorData(data); 
      return ResponseEntity.ok(consultas); 
    } 
    
  //Query Method 
    @GetMapping("/hora/{hora}") 
    public ResponseEntity<List<Consulta>> buscarConsultasPorHora(@PathVariable String hora) { 
      List<Consulta> consultas = consultaService.buscarConsultaPorHora(hora); 
      return ResponseEntity.ok(consultas); 
    } 

  //Query Method 
    @GetMapping("/veterinario/{veterinario}") 
    public ResponseEntity<List<Consulta>> buscarConsultasPorVeterinario(@PathVariable String veterinario) { 
      List<Consulta> consultas = consultaService.buscarConsultaPorVeterinario(veterinario); 
      return ResponseEntity.ok(consultas); 
    } 
    
  //Query Method 
    @GetMapping("/pet/{pet}") 
    public ResponseEntity<List<Consulta>> buscarConsultasPorPet(@PathVariable String pet) { 
      List<Consulta> consultas = consultaService.buscarConsultaPorPet(pet); 
      return ResponseEntity.ok(consultas); 
    } 

    @GetMapping("/{id}") 
    public ResponseEntity<Consulta> getConsultaById(@PathVariable Long id) { 
        Consulta consulta = consultaService.getConsultaById(id); 
        if (consulta != null) { 
            return ResponseEntity.ok(consulta); 
        } else { 
            return ResponseEntity.notFound().build(); 
        } 
    } 

    public ResponseEntity<List<Consulta>> getAllConsultas() { 
        List<Consulta> consultas = consultaService.getAllConsulta(); 
        return ResponseEntity.ok(consultas); 
    } 

    @PostMapping("/") 
    public ResponseEntity<Consulta> criarConsulta(@RequestBody @Valid Consulta consulta) { 
        Consulta criarConsulta = consultaService.salvarConsulta(consulta); 
        return ResponseEntity.status(HttpStatus.CREATED).body(criarConsulta); 
    } 
    
    @PutMapping("/{id}") 
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Long id, @RequestBody @Valid Consulta consulta) { 
        Consulta updatedConsulta = consultaService.updateConsulta(id, consulta); 
        if (updatedConsulta != null) { 
            return ResponseEntity.ok(consulta); 
        } else { 
            return ResponseEntity.notFound().build(); 
        } 
    } 
    @DeleteMapping("/{id}") 
    public ResponseEntity<Consulta> deleteConsulta(@PathVariable Long id) { 
        boolean deleted = consultaService.deleteConsulta(id); 
        if (deleted) { 
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  
        } else { 
            return ResponseEntity.notFound().build(); 
        } 
    }   
} 