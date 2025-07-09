<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Área do Médico</title>
    <%-- Seus estilos CSS permanecem os mesmos --%>
    <style>
        body { margin: 0; padding: 0; background: #f8f9fa; font-family: "Segoe UI", sans-serif; }
        header { background-color: #4fa7d8; padding: 16px 24px; color: #fff; display: flex; justify-content: space-between; align-items: center; }
        header h1 { margin: 0; font-size: 20px; }
        nav a { color: white; text-decoration: none; font-weight: bold; margin-left: 16px; }
        .container { display: flex; justify-content: center; align-items: flex-start; padding: 40px 16px; }
        .card { background-color: #fff; border-radius: 12px; box-shadow: 0 8px 20px rgba(0,0,0,0.1); width: 100%; max-width: 1000px; overflow: hidden; }
        .card-header { background-color: #4fa7d8; padding: 20px; color: #fff; }
        .card-body { padding: 24px; }
        .card-footer { padding: 16px; background-color: #f0f0f0; text-align: center; font-size: 0.9rem; }
        .table { width: 100%; border-collapse: collapse; background-color: white; border-radius: 8px; overflow: hidden; margin-top: 16px; }
        .table th, .table td { padding: 12px 16px; text-align: left; border-bottom: 1px solid #eee; }
        .table th { background-color: #f0f0f0; }
        .btn { padding: 6px 12px; border: none; border-radius: 6px; font-weight: bold; cursor: pointer; font-size: 14px; text-decoration: none; display: inline-block; }
        .btn-outline-primary { color: #0d6efd; border: 1px solid #0d6efd; background-color: transparent; }
        .btn-outline-primary:hover { background-color: #0d6efd; color: #fff; }
        .alert { background-color: #fff3cd; color: #856404; padding: 12px; border-radius: 6px; margin-bottom: 16px; border: 1px solid #ffeeba; }
    </style>
</head>
<body>

<header>
    <h1>Clínica</h1>
    <nav>
        <a href="${pageContext.request.contextPath}/medico/meusPacientes">Meus Pacientes</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </nav>
</header>

<div class="container">
    <div class="card">
        <div class="card-header">
            <h2>Área do Médico</h2>
        </div>
        <div class="card-body">
            <p>Olá, ${sessionScope.usuarioLogado.nome}. Aqui você pode visualizar e gerenciar suas consultas.</p>

            <c:if test="${not empty msgSucesso}">
                <div class="alert" style="background-color: #d1e7dd; color: #0f5132; border-color: #badbcc;">
                        ${msgSucesso}
                </div>
            </c:if>

            <c:if test="${empty consultas}">
                <div class="alert">
                    Nenhuma consulta marcada no momento.
                </div>
            </c:if>

            <c:if test="${not empty consultas}">
                <h4>Consultas Marcadas</h4>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Data e Hora</th>
                        <th>Paciente</th>
                        <th>Tipo</th>
                        <th>Status</th>
                        <th>Observação</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="c" items="${consultas}">
                        <tr>
                            <td><c:out value="${c.dataHorario}" /></td>
                            <td><c:out value="${c.paciente.nome}" /></td>
                            <td><c:out value="${c.tipoConsulta}" /></td>
                            <td><c:out value="${c.status}" /></td>
                            <td><c:out value="${c.pbservacao}" /></td>
                            <td>
                                <a class="btn btn-outline-primary"
                                   href="${pageContext.request.contextPath}/medico/editar?id=${c.id}">
                                    Editar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
        <div class="card-footer">
            Painel do médico - Consultas agendadas
        </div>
    </div>
</div>

</body>
</html>