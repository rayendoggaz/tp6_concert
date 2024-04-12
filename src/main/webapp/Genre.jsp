<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des genres</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <%@include file="header.jsp" %>
    <div class="container">
        <div class="card">
            <div class="card-header">
                Liste des genres
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <tr>
                        <th>ID</th><th>Genre</th><th>Date de Création</th><th>Suppression</th><th>Edition</th>
                    </tr>
                    <c:forEach items="${model.genres}" var="genre">
                        <tr>
                            <td>${genre.idGenre}</td>
                            <td>${genre.nomGenre}</td>
                            <td>${genre.dateCreation}</td>
                            <td><a onclick="return confirm('Êtes-vous sûr ?')" href="supprimerGenre?id=${genre.idGenre}">Supprimer</a></td>
                            <td><a href="editerGenre?id=${genre.idGenre}">Éditer</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
