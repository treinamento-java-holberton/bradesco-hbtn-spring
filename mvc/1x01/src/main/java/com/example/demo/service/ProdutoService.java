package com.example.demo.service;


import com.example.demo.model.Produto;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();
    private Long contadorId = 1L;


    public List<Produto> listarProdutos() {
        return produtos;
    }


    public Produto adicionarProduto(Produto produto) {
        produto.setId(contadorId++);
        produtos.add(produto);
        return produto;
    }


    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        var produtoOpt = produtos.stream().filter(p -> p.getId().equals(id)).findFirst();

        if (produtoOpt.isPresent()) {
            var produtoAtual = produtoOpt.get();
            int index = produtos.indexOf(produtoAtual);
            produtoAtualizado.setId(id);
            produtos.set(index, produtoAtualizado);
        }
        return produtoAtualizado;
    }


    public boolean deletarProduto(Long id) {
        boolean result = false;
        var produtoOpt = produtos.stream().filter(p -> p.getId().equals(id)).findFirst();

        if (produtoOpt.isPresent()) {
            result = true;
            produtos.remove(produtoOpt.get());
        }
        return result;
    }
}