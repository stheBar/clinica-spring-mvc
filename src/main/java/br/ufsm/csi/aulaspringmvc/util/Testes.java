package br.ufsm.csi.aulaspringmvc.util;

import java.util.ArrayList;

public class Testes {

//    public static void main(String[] args) {
//        testesUsuarioDAO();
//        //testesClienteDAO();
//    }
//
//    public static void testesUsuarioDAO() {
//        UsuarioDAO dao = new UsuarioDAO();
//
//        //Criar usuario
//        Usuario usuario = new Usuario();
//
//        usuario.setEmail("maran@teste");
//        usuario.setSenha("123");
//        usuario.setAtivo(true);
//
//        dao.inserir(usuario);
//
//        //Listar usuario
//       // imprimirUsuarios(dao.listar());
//
//        //Alterar usuario
//        /*usuario.setId(1);
//        usuario.setEmail("pedro@teste.editado");
//        usuario.setSenha("senha editada");
//        usuario.setAtivo(false);
//
//        dao.alterar(usuario);
//
//        //Listar usuario
//        imprimirUsuarios(dao.listar());*/
//    }
//
//    public static void testesClienteDAO() {
//        ClienteDAO clienteDAO = new ClienteDAO();
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
//
//        //Criar cliente
//        Cliente cliente = new Cliente();
//        Usuario usuario = usuarioDAO.buscar("pedro@teste.editado");
//
//        cliente.setNome("Pedro");
//        cliente.setCpf("12345678900");
//        cliente.setTelefone("1234567890");
//        cliente.setEndereco("Rua teste");
//        cliente.setUsuarioId(usuario.getId());
//
//        clienteDAO.inserir(cliente);
//
//        //Listar cliente
//        imprimir(clienteDAO.listar());
//
//        //Alterar cliente
//        cliente.setId(1);
//        cliente.setNome("Pedro editado");
//
//        clienteDAO.alterar(cliente);
//
//        //Listar cliente
//        imprimir(clienteDAO.listar());
//    }
//
//    public static void imprimirUsuario(Usuario usuario) {
//        System.out.println(
//                "Id: " + usuario.getId()
//                        + " email: " + usuario.getEmail()
//                        + " senha: " + usuario.getSenha());
//    }
//
//    public static void imprimirUsuarios(ArrayList<Usuario> usuarios) {
//        for (Usuario usuario : usuarios) {
//            imprimirUsuario(usuario);
//        }
//    }
//
//    public static void imprimirCliente(Cliente cliente) {
//        System.out.println(
//                "Id: " + cliente.getId()
//                        + " Nome: " + cliente.getNome()
//                        + " CPF: " + cliente.getCpf()
//                        + " Telefone: " + cliente.getTelefone()
//                        + " Endereco: " + cliente.getEndereco()
//                        + " UsuarioId: " + cliente.getUsuarioId());
//    }
//
//    public static void imprimir(ArrayList<Cliente> clientes) {
//        for (Cliente cliente : clientes) {
//            imprimirCliente(cliente);
//        }
//    }

}
