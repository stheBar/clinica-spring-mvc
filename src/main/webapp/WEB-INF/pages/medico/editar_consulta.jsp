<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Editar Consulta</title>
    <%-- Seus estilos CSS permanecem os mesmos --%>
    <style>
        body { margin: 0; padding: 0; background: #f8f9fa; font-family: "Segoe UI", sans-serif; }
        .container { display: flex; justify-content: center; align-items: center; padding: 40px 16px; min-height: 100vh; }
        .card { background-color: #fff; border-radius: 12px; box-shadow: 0 8px 20px rgba(0,0,0,0.1); width: 100%; max-width: 600px; overflow: hidden; }
        .card-header { background-color: #4fa7d8; padding: 20px; color: #fff; text-align: center; }
        .card-body { padding: 24px; }
        .form-label { font-weight: 600; color: #333; display: block; margin-bottom: 5px; }
        .form-control { width: 100%; padding: 10px 14px; font-size: 1rem; border: 1px solid #ccc; border-radius: 8px; margin-bottom: 16px; box-sizing: border-box; }
        .form-control:focus { outline: none; border-color: #4fa7d8; box-shadow: 0 0 0 2px rgba(79, 167, 216, 0.2); }
        .btn-primary { background-color: #4fa7d8; border: none; color: #fff; padding: 10px 20px; border-radius: 8px; font-size: 1rem; font-weight: bold; cursor: pointer; transition: background-color 0.3s ease; text-decoration: none;}
        .btn-primary:hover { background-color: #3d91c2; }
        .btn-secondary { margin-left: 10px; padding: 10px 20px; border-radius: 8px; font-size: 1rem; font-weight: bold; background-color: #dee2e6; color: #333; border: none; cursor: pointer; text-decoration: none; }
        .alert { background-color: #f8d7da; color: #842029; padding: 12px; border-radius: 6px; margin: 20px 0; }
    </style>
</head>
<body>

<div class="container">
    <div class="card">
        <div class="card-header">
            <h4>Editar Consulta</h4>
        </div>
        <div class="card-body">

            <form method="post" action="${pageContext.request.contextPath}/medico/salvar">
                <input type="hidden" name="id" value="${consulta.id}"/>

                <label class="form-label">Paciente</label>
                <input type="text" class="form-control" value="${consulta.paciente.nome}" disabled/>

                <label class="form-label">Status</label>
                <input type="text" class="form-control" name="status" value="${consulta.status}" required/>

                <label class="form-label">Observação</label>
                <textarea class="form-control" name="observacao" rows="3">${consulta.pbservacao}</textarea>

                <div>
                    <button type="submit" class="btn-primary">Salvar</button>
                    <a href="${pageContext.request.contextPath}/medico" class="btn-secondary">Cancelar</a>
                </div>
            </form>

        </div>
    </div>
</div>

</body>
</html>