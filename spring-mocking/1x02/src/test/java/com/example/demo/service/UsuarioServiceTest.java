package com.example.demo.service;


import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class UsuarioServiceTest {


    @Mock
    private UsuarioRepository usuarioRepository;


    @InjectMocks
    private UsuarioService usuarioService;

    AutoCloseable mockContext;

    @BeforeEach
    void setUp() {
        mockContext = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockContext.close();
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        var usuario = new Usuario();
        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));

        assertEquals(usuario, usuarioService.buscarUsuarioPorId(1L));
        verify(usuarioRepository).findById(1L);
    }


    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        var exception = assertThrows(RuntimeException.class, () -> usuarioService.buscarUsuarioPorId(1L));
        assertEquals("Usuario não encontrado", exception.getMessage());
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        var usuario = new Usuario();
        assertDoesNotThrow(() -> usuarioService.salvarUsuario(usuario));
        verify(usuarioRepository).save(usuario);
    }
}
