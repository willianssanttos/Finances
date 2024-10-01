package br.com.sistema.controle.financas.pessoais.adapter.output.usuario;

import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.port.output.usuario.IUsuarioDao;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UsuarioDaoImpl implements IUsuarioDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioDaoImpl.class);

    @Override
    public UsuarioEntity criarUsuario(UsuarioEntity usuario){
        logger.debug(Constantes.DebugRegistroProcesso + usuario);
        try {
            String sql = "SELECT inserir_usuario(?,?,?,?)";
            jdbcTemplate.queryForObject(sql,new Object[]{
                    usuario.getNomeUsuario(), usuario.getEmailUsuario(), usuario.getSenhaUsuario(),usuario.getNumeroCelular()
            },Integer.class);

           logger.info(Constantes.InfoRegistrar + usuario);
        } catch (Exception e){
            logger.error(Constantes.ErroRegistrarNoServidor);
        }
        return usuario;
    }
//    public boolean verificarEmailExistente(String email) {
//        logger.debug(Constantes.ErroBuscarRegistroNoServidor + email);
//        try{
//        String sql = "SELECT verificar_email_existente(?)";
//        boolean emailExiste = false;
//
//        return jdbcTemplate.queryForObject(sql, Boolean.class,emailExiste);
//            logger.info(Constantes.InfoBuscar + email);
//        } catch (Exception e) {
//            logger.error(Constantes.ErroBuscarRegistroNoServidor);
//        }
//        return email;
//
//    }
//
//    public UsuarioEntity validarLogin(String email) {
//        logger.debug(Constantes.DebugBuscarProcesso + email);
//
//        String sql = "SELECT * FROM validar_login(?)";
//
//       UsuarioEntity usuario = null;
//
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, email);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                usuario = new UsuarioEntity();
//
//                usuario.setIdUsuario(rs.getInt("nr_id_usuario"));
//                usuario.setEmailUsuario(rs.getString("ds_email"));
//                usuario.setSenhaUsuario(rs.getString("ds_senha"));
//            }
//            rs.close();
//
//            logger.info(Constantes.InfoBuscar + email);
//        } catch (SQLException e) {
//            logger.error(Constantes.ErroBuscarRegistroNoServidor);
//        }
//        return usuario;
//    }
}
