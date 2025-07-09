<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Consultas Agendadas</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: #f8f9fa;
            font-family: "Segoe UI", sans-serif;
        }

        header {
            background-color: #4fa7d8;
            padding: 16px 24px;
            color: #fff;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header h1 {
            margin: 0;
            font-size: 20px;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding: 40px 16px;
        }

        .card {
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 1000px;
            overflow: hidden;
        }

        .card-header {
            background-color: #4fa7d8;
            padding: 20px;
            color: #fff;
        }

        .card-body {
            padding: 24px;
        }

        .card-footer {
            padding: 16px;
            background-color: #f0f0f0;
            text-align: center;
            font-size: 0.9rem;
        }

        .header-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .welcome {
            font-size: 14px;
            color: #666;
        }

        .btn {
            padding: 8px 14px;
            border: none;
            border-radius: 6px;
            font-weight: bold;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            margin-left: 8px;
        }

        .btn-success {
            background-color: #4fa7d8;
            color: white;
        }

        .btn-warning {
            background-color: #ffc107;
            color: black;
        }

        .btn-danger {
            background-color: #dc3545;
            color: white;
        }

        .table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
            margin-top: 16px;
        }

        .table th, .table td {
            padding: 12px 16px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }

        .table th {
            background-color: #f0f0f0;
        }

        .alert {
            background-color: #f8d7da;
            color: #842029;
            padding: 12px;
            border-radius: 6px;
            margin-bottom: 16px;
            position: relative;
        }

        .btn-close {
            position: absolute;
            right: 12px;
            top: 10px;
            background: none;
            border: none;
            font-size: 1.2rem;
            color: #842029;
            cursor: pointer;
        }

        .actions {
            display: flex;
            gap: 8px;
        }

        form {
            display: inline;
        }
    </style>
</head>
<body>

<header>
    <h1>Clínica</h1>
    <nav>
        <a href="<c:url value='/admin/consulta'/>" class="btn btn-success">Consulta</a>
        <a href="<c:url value='/admin/medico'/>" class="btn btn-success">Médico</a>
        <a href="<c:url value='/admin/paciente'/>" class="btn btn-success">Paciente</a>
    </nav>
</header>

<div class="container">
    <div class="card">
        <div class="card-header">
            <h2 class="mb-0">Consultas Agendadas</h2>
        </div>
        <div class="card-body">

            <p class="welcome">Bem-vindo, <strong>${usuarioLogado.nome}</strong>! Você está no painel do administrador.</p>

            <div class="header-row">
                <span></span>
                <a href="<c:url value='/admin/consulta/form'/>" class="btn btn-success">+ Nova Consulta</a>
            </div>

            <c:if test="${not empty msg_success}">
                <div class="alert alert-success">
                        ${msg_success}
                    <button type="button" class="btn-close" onclick="this.parentElement.style.display='none';">&times;</button>
                </div>
            </c:if>
            <c:if test="${not empty msg_error}">
                <div class="alert alert-danger">
                        ${msg_error}
                    <button type="button" class="btn-close" onclick="this.parentElement.style.display='none';">&times;</button>
                </div>
            </c:if>

            <table class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Paciente</th>
                    <th>Médico</th>
                    <th>Data/Hora</th>
                    <th>Status</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${consultas}">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.paciente.nome}</td>
                        <td>${c.medico.nome}</td>
                        <td>${horarioMap[c.id]}</td>
                        <td>${c.status}</td>
                        <td class="actions">
                            <a href="<c:url value='/admin/consulta/form/${c.id}'/>" class="btn btn-warning">Editar</a>

                            <form method="post" action="<c:url value='/admin/consulta/excluir/${c.id}'/>" onsubmit="return confirm('Tem certeza que deseja excluir esta consulta?');">
                                <button type="submit" class="btn btn-danger">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="card-footer">
            Painel de administração da clínica - Agendamentos
        </div>
    </div>
</div>

</body>
</html>
