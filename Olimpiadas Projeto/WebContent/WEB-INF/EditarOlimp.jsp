<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<title>Cadastro de Olimpiada</title>
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">Olimpiadas</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="PaisCadastro.jsp">Países</a>
                    </li>
                    <li><a href="ModalidadeCadastro.jsp">Modalidades</a>
                    </li>
                </ul>
            </div>
        </div>
    	</nav>
		<br><br>
		<div class="container">
			<h1 class="page-header">Cadastro de Olimpiada</h1>
			<form action="ManterOlimpiada.do" method="post">
				<div class="row">
					<div class="form-group col-md-12">
							<label><strong>Pais</strong></label>
							<input type="text" class="form-control" name="pais" value="${pais.nome }">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
							<label><strong>Modalidade</strong></label>
							<input type="text" class="form-control" name="modalidade" value="${modalidade.nome }">
					</div>
					<div class="form-group col-md-6">
							<label><strong>Ano</strong></label>
							<input type="text" class="form-control" name="ano" value="${olimpiada.ano}">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-3">
							
					</div>
					<div class="form-group col-md-2">
							<label><strong>Ouro</strong></label>
							<input type="text" class="form-control" name="ouro" value="0">
					</div>
					<div class="form-group col-md-2">
							<label><strong>Prata</strong></label>
							<input type="text" class="form-control" name="prata" value="0">
					</div>
					<div class="form-group col-md-2">
							<label><strong>Bronze</strong></label>
							<input type="text" class="form-control" name="bronze" value="0">
					</div>
				</div>
				<hr />
				<div class="row text-right">
	                	<div class="col-md-12">
	                    	<button type="submit" class="btn btn-primary" name="acao" value="criarOlimp">Editar</button>	
	                    		<a href="index.html" class="btn btn-default">Cancelar</a>
	               		</div>
	           	</div>
	           	
	           	</form>
		</div>
		
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>