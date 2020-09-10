package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Provincia;
import com.roylan.expedientes_etp.database.entities.Usuario;
import com.roylan.expedientes_etp.database.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a los usuarios registrados.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarUsuarioImpl implements IGestionarService<Usuario>, UserDetailsService {

    @Autowired
    private UsuarioRepository u_repo;

    @Autowired
    private HttpServletRequest request;

    public GestionarUsuarioImpl() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo usuario.
     *
     * @param e Nuevo usuario.
     */
    @CacheEvict(cacheNames = {"lst_usuarios", "lst_usuarios_sp"}, allEntries = true)
    public void adicionar(Usuario e) {
        u_repo.save(e);
    }

    /**
     * Esta funcionalidad permite obtener un usuario mediante su identificador.
     *
     * @param idU Identificador del usuario.
     * @return <code>Usuario</code> Usuario obtenido.
     * <code>null</code> Si no existe el usuario.
     */
    @Cacheable(cacheNames = "usuario_id")
    public Usuario obtenerId(long idU) {
        return u_repo.findByIdUsuario(idU);
    }

    /**
     * Esta funcionalidad permite obtener un usuario mediante su nombre.
     *
     * @param nombU Nombre del usuario.
     * @return <code>Usuario</code> Usuario obtenido.
     * <code>null</code> Si no existe el usuario.
     */
    @Cacheable(cacheNames = "usuario_nomb")
    public Usuario obtenerNomb(String nombU) {
        return u_repo.findByNombUsuario(nombU);
    }

    /**
     * Esta funcionalidad permite editar un usuario.
     *
     * @param idU     Identificador del usuario a editar.
     * @param n_datos Nuevos datos del usuario.
     */
    @CacheEvict(cacheNames = {"lst_usuarios", "lst_usuarios_sp", "usuario_id", "usuario_nomb"}, allEntries = true)
    public void update(long idU, Usuario n_datos) {
        u_repo.updateUsuario(idU, n_datos.getNombDescriptivo(), n_datos.getPassword(), n_datos.getEstado(), n_datos.getMcpio(), n_datos.getRol());
    }

    /**
     * Esta funcionalidad permite eliminar un usuario.
     *
     * @param idU Identificador del usuario.
     */
    @CacheEvict(cacheNames = {"lst_usuarios", "lst_usuarios_sp", "usuario_id", "usuario_nomb"}, allEntries = true)
    public void eliminar(long idU) {
        Usuario e = obtenerId(idU);

        u_repo.delete(e);
    }

    /**
     * Esta funcionalidad permite listar todos los usuarios registrados.
     *
     * @return <code>List</code> Todos los usuarios registrados.
     */
    @Cacheable(cacheNames = "lst_usuarios")
    public List<Usuario> listar() {
        return u_repo.findAllByOrderByRolAscMcpioAscNombUsuarioAsc();
    }

    /**
     * Esta funcionalidad permite listar todos los usuarios registrados en una provincia y que no presenten un determinado rol.
     *
     * @param rol  Tipo de rol que no presentan.
     * @param prov Provincia.
     * @return <code>List</code> Todos los usuarios registrados en esa provincia.
     */
    @Cacheable(cacheNames = "lst_usuarios_sp")
    public List<Usuario> listarSegunProvincia(String rol, Provincia prov) {
        return u_repo.findAllByRol_TipoRolIsNotAndMcpioProvOrderByRolAscMcpioAscNombUsuarioAsc(rol, prov);
    }

    /**
     * Esta funcionalidad permite obtener de la BD al usuario que intenta autenticarse.
     *
     * @param nombU Nombre de usuario.
     * @return <code>UserDetails</code> Usuario.
     * @throws UsernameNotFoundException Si el nombre de usuario no se encuentra registrado.
     */
    public UserDetails loadUserByUsername(String nombU) throws UsernameNotFoundException {

        //Busca el usuario en la BD y si no existe lanza una exepción
        Usuario u = u_repo.findByNombUsuario(nombU);
        if (u == null) {
            throw new UsernameNotFoundException("Usuario desconocido!!");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(u.getRol().getTipoRol()));

        HttpSession session = request.getSession();
        session.setAttribute("usuarioAutenticado", u);

        return new User(u.getNombUsuario(), u.getPassword(), u.getEstado(), true, true, true, roles);
    }
}