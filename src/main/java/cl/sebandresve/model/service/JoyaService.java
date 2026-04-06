package cl.sebandresve.model.service;

import cl.sebandresve.model.dao.JoyaDAO;
import cl.sebandresve.model.vo.JoyaVO;

import java.util.List;

public class JoyaService {

    private JoyaDAO dao = new JoyaDAO();

    public void registrarJoya(JoyaVO joya) {

        if (joya.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        if (joya.getStock() < 0) {
            throw new IllegalArgumentException("Stock inválido");
        }

        dao.crear(joya);
    }

    public List<JoyaVO> obtenerJoyas() {
        return dao.listar();
    }
}
