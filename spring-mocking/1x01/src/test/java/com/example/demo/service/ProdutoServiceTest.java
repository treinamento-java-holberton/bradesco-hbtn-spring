package com.example.demo.service;


import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

        assertThat(produtoService.buscarPorId(1L)).isEqualTo(produto);
        verify(produtoRepository).findById(1L);
    }


    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {

        assertThatThrownBy(() -> produtoService.buscarPorId(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Produto não encontrado");
        verify(produtoRepository).findById(1L);
    }
}