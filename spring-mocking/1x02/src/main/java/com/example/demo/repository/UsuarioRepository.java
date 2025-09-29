package com.example.demo.repository;


import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository {

    Optional<Usuario> findById(Long id);

    Usuario save(@NonNull Usuario usuario);
}