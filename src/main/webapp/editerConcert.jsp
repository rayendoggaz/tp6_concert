<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Modification des Concerts</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <%@ include file="header.jsp"%>
    <p></p>
    <div class="container">
        <div class="card">
            <div class="card-header">Modification des Concerts</div>
            <div class="card-body">
                <form action="updateConcert.do" method="post">
                    <div hidden class="form-group">
                        <label class="control-label">ID Concert :</label>
                        <input type="text" name="id" class="form-control" value="${concert.idConcert}" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Nom Concert :</label>
                        <input type="text" name="nom" class="form-control" value="${concert.nomConcert}" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Prix :</label>
                        <input type="text" name="prix" class="form-control" value="${concert.prix}" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Genre :</label>
                        <select name="genre" class="form-control">
                            <option value="${concert.genre.idGenre}" selected>${concert.genre.nomGenre}</option>
                            <c:forEach items="${genreModel.genres}" var="genre">
                                <c:if test="${genre.idGenre != concert.genre.idGenre}">
                                    <option value="${genre.idGenre}">${genre.nomGenre}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary">Modifier</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
