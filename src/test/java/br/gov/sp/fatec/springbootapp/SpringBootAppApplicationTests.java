package br.gov.sp.fatec.springbootapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AutorizacaoRepository autorizacaoRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void testaInsercaoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Arthur2");
        usuario.setSenha("teste");
        usuario.setAutorizacoes(new HashSet<Autorizacao>());

        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_ADMIN2");
        autorizacaoRepo.save(aut);
        usuario.getAutorizacoes().add(aut);

        usuarioRepo.save(usuario);
        assertNotNull(usuario.getAutorizacoes().iterator().next().getId());
    }

    @Test
    void testaInsercaoAutorizacao() {
        Usuario usuario = new Usuario();
        usuario.setNome("Arthur3");
        usuario.setSenha("teste");
        usuarioRepo.save(usuario);

        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_ADMIN3");
        aut.setUsuarios(new HashSet<Usuario>());
        aut.getUsuarios().add(usuario);
        autorizacaoRepo.save(aut);

        assertNotNull(aut.getUsuarios().iterator().next().getId());
    }

    @Test
    void testaAutorizacao() {
        Usuario usuario = usuarioRepo.findById(1L).get();
        assertEquals("ROLE_ADMIN", usuario.getAutorizacoes().iterator().next().getNome());
    }

    @Test
    void testaUsuario() {
        Autorizacao aut = autorizacaoRepo.findById(1L).get();
        assertEquals("Arthur", aut.getUsuarios().iterator().next().getNome());
    }

    @Test
    void testaBuscaUsuarioNomeContains() {
        List<Usuario> usuarios = usuarioRepo.findByNomeContainsIgnoreCase("r");
        assertFalse(usuarios.isEmpty());
    }

    @Test
    void testaBuscaUsuarioNome() {
        Usuario usuario = usuarioRepo.findByNome("Arthur");
        assertNotNull(usuario);
    }

    @Test
    void testaBuscaUsuarioNomeSenha() {
        Usuario usuario = usuarioRepo.findByNomeAndSenha("Arthur", "teste");
        assertNotNull(usuario);
    }

    @Test
    void testaBuscaUsuarioNomeAutorizacao() {
        List<Usuario> usuarios = usuarioRepo.findByAutorizacoesNome("ROLE_ADMIN");
        assertFalse(usuarios.isEmpty());
    }

}
