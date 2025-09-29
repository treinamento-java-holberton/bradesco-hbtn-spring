package com.example.demo.service;


import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {


    @Mock
    private ProdutoRepository produtoRepository;


    @InjectMocks
    private ProdutoService produtoService;


    @Test
    void deveRetornarProdutoQuandoIdExistir() {
        var produto = new Produto();
        when(produtoRepository.findById(any())).thenReturn(Optional.of(produto));

        assertEquals(produto, produtoService.buscarPorId(1L));
        verify(produtoRepository).findById(1L);
    }


    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {
        var exception = assertThrows(RuntimeException.class, () -> produtoService.buscarPorId(1L));
        assertEquals("Produto não encontrado", exception.getMessage());
        verify(produtoRepository).findById(1L);
    }
}