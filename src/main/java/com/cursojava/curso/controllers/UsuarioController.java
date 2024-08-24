package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(value="api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setNombre("Marcos");
        usuario.setApellido("Pividori");
        usuario.setEmail("mpividori@gmail.com");
        usuario.setTelefono("123456789");
        return usuario;
    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value="usuario12")
    public Usuario modificar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Marcos");
        usuario.setApellido("Pividori");
        usuario.setEmail("mpividori@gmail.com");
        usuario.setTelefono("123456789");
        return usuario;
    }
    @RequestMapping(value="api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        usuarioDao.eliminar(id);
    }
    @RequestMapping(value="usuario9888")
    public Usuario buscar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Marcos");
        usuario.setApellido("Pividori");
        usuario.setEmail("mpividori@gmail.com");
        usuario.setTelefono("123456789");
        return usuario;
    }
}
