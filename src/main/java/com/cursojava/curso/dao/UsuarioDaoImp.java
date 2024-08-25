package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
    //Inyecta un EntityManager, que es el API de JPA que se utiliza para interactuar con la BD.
    // Proporciona métodos para realizar operaciones CRUD.
    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional //asegura que los métodos dentro de esta clase se ejecuten dentro de una transacción.
    //Si ocurre una excepción, la transacción se revertirá automáticamente para evitar datos inconsistentes
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario u";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email=:email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email",usuario.getEmail())
                .getResultList();

        if(lista.isEmpty()){
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0);
        }else{
            return null;
        }
    }
}
