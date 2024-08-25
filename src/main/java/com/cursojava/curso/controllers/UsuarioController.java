package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTutil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //Indica que esta clase es un controlador REST,
// lo que significa que maneja solicitudes HTTP y responde con datos JSON

public class UsuarioController {

    @Autowired //se utiliza para inyectar automáticamente las dependencias gestionadas por Spring.
    // En este caso, UsuarioDao y JWTutil son inyectados para ser utilizados en el controlador.
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTutil jwtutil;

    @RequestMapping(value="api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setNombre("Marcos");
        usuario.setApellido("Pividori");
        usuario.setEmail("mpividori@gmail.com");
        usuario.setTelefono("123456789");
        return usuario;
    }
    //@RequestMapping: Define una ruta API para acceder a este metodo.
    //La ruta api/usuarios acepta solicitudes GET.
    @RequestMapping(value="api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){

        if(!validarToken(token)){
            return null;
        }
        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token){
        String usuarioId = jwtutil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value="api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token,
                         @PathVariable Long id){
        if(!validarToken(token)){
            return;
        }
        usuarioDao.eliminar(id);
    }
}
