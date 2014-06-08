<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
    	
    	<meta name="author" content="Pedro Almir">
    	<meta name="description" content="A visual tool to solve the knapsack problem">
    	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">
    	
		<!-- Bootstrap core CSS -->
	    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	    <!-- Custom styles for this template -->
	    <link href="${pageContext.request.contextPath}/css/sticky-footer-navbar.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/vis.css" rel="stylesheet" type="text/css" />
	
	    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
	      	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	      	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
	    <!-- ========================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
	    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/docs.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.js"></script>
	    <script src="${pageContext.request.contextPath}/js/main.js"></script>
	</head>
	<body>
		<!-- Fixed navbar -->
    	<div class="navbar navbar-default navbar-fixed-top">
      		<div class="container">
        		<div class="navbar-header">
          			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	           			<span class="sr-only">Toggle navigation</span>
	            		<span class="icon-bar"></span>
	            		<span class="icon-bar"></span>
	            		<span class="icon-bar"></span>
          			</button>
          			<a class="navbar-brand" href="${pageContext.request.contextPath}/">A visual tool to solve the 0/1 knapsack problem</a>
        		</div>
        		<div class="collapse navbar-collapse">
          			<ul class="nav navbar-nav">
            			<li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
            			<li><a href="http://github.com/pedroalmir/knapsackproblem">Git Repository</a></li>
            			<li><a href="mailto:petrus.cc@gmail.com">Contact</a></li>
          			</ul>
        		</div><!--/.nav-collapse -->
      		</div>
    	</div>
    	<!-- Begin page content -->
    	<div class="container">
      		<div class="page-header">
        		<h2>Knapsack problem</h2>
      		</div>
      		<p class="lead text-justify">The <code>knapsack problem</code> or rucksack problem is a problem in <a href="http://en.wikipedia.org/wiki/Combinatorial_optimization">combinatorial 
      		optimization</a>: Given a set of items, each with a mass and a value, determine the number of each item to include in a collection so that the total weight 
      		is less than or equal to a given limit and the total value is as large as possible. It derives its name from the problem faced by someone who is constrained by a 
      		fixed-size knapsack and must fill it with the most valuable items.</p>
      		
			<p class="lead text-justify">
			The problem often arises in resource allocation where there are financial constraints and is studied in fields such as combinatorics, 
			computer science, complexity theory, cryptography and applied mathematics.</p>
			
			<p class="lead text-justify">
			This tool features four different techniques for solving the 0/1 knapsack problem: <code>Brute force algorithm</code>, <code>Greedy Algorithm</code>,
			<code>Ant Colony Optimization</code>, <code>Genetic algorithm</code>.
			</p>
			
      		<div class="row">
      			<div class="col-md-6">
      				<div class="panel panel-default">
						<div class="panel-heading">
					    	<h3 class="panel-title">Knapsack problem definition</h3>
					  	</div>
					  	<div class="panel-body">
					  		<form action="">
					  			<div class="col-sm-12">
								  	<div class="form-group">
								    	<label for="firstNode">Knapsack problem restriction</label>
								    	<input type="text" class="form-control" id="restriction" placeholder="Define here the knapsack problem restriction" value="60">
								  	</div>
							  	</div>
					  			<div class="col-sm-5">
					  				<div class="form-group">
								    	<label for="alpha">Item weight</label>
								    	<input type="text" class="form-control" id="weight" placeholder="Item weight" value="">
							    	</div>
					  			</div>
					  			<div class="col-sm-5">
					  				<div class="form-group">
								    	<label for="alpha">Gain</label>
								    	<input type="text" class="form-control" id="gain" placeholder="Item gain value" value="">
							    	</div>
					  			</div>
					  			<div class="col-sm-2">
					  				<button id="addItem" type="button" class="btn btn-primary" style="width: 100%; margin-top: 23px;">
										<span class="glyphicon glyphicon-plus"></span>
									</button>
					  			</div>
					  			<div class="col-sm-12">
						  			<div class="form-group">
								    	<label for="listOfItems">List of Items (Use JSON pattern)</label>
							    		<textarea class="form-control" rows="5" id="listOfItems" placeholder="Define here the matrix of problem">[{"id": 1, "weight": 52, "gain": 100},{"id": 2, "weight": 23, "gain": 60},{"id": 3, "weight": 35, "gain": 70},{"id": 4, "weight": 15, "gain": 15},{"id": 5, "weight": 7, "gain": 15}]</textarea>
								  	</div>
							  	</div>
					  		</form>
					  	</div>
					</div>
      			</div>
      			<div class="col-md-6">
      				<div class="panel panel-default">
						<div class="panel-heading">
					    	<h3 class="panel-title">Algorithm configuration</h3>
					  	</div>
					  	<div class="panel-body">
					  		<form action="">
					  			<div class="col-sm-8">
							    	<div class="form-group">
								    	<label for="strategy">Algorithm</label>
								    	<select class="form-control" id="algorithm">
								    		<option value="none">Choose an algorithm</option>
										  	<option value="brute_force">Brute force</option>
										  	<option value="greedy">Greedy Algorithm</option>
										  	<option value="aco">Ant Colony Optimization</option>
										  	<option value="genetic">Genetic Algorithm</option>
										</select>
							    	</div>
							  	</div>
							  	<div class="col-sm-4">
						    		<button id="execute" type="button" class="btn btn-primary" data-loading-text="Executing..." style="margin-top: 23px; width: 100%;">
						    			Execute <span class="glyphicon glyphicon-play"></span>
						    		</button>
							    </div>
					  		</form>
					  	</div>
					</div>
					<!-- Ant Colony Optimization -->
					<div id="antSettingsPanel" class="panel panel-default settingPanel" style="display: none;">
						<div class="panel-heading">
					    	<h3 class="panel-title">Ant Colony Optimization Settings</h3>
					  	</div>
					  	<div class="panel-body">
					  		<form action="">
							  	<div class="col-sm-6">
							  		<div class="form-group">
								    	<label for="alpha">Alpha</label>
								    	<input type="text" class="form-control" id="alpha" placeholder="Alpha value" value="1.0">
							    	</div>
							    </div>
							    <div class="col-sm-6">
							    	<div class="form-group">
								    	<label for="beta">Beta</label>
								    	<input type="text" class="form-control" id="beta" placeholder="Beta value" value="2.0">
							    	</div>
							  	</div>
							  	<div class="col-sm-6">
							  		<div class="form-group">
								    	<label for="initialPheromone">Initial Pheromone Trails</label>
								    	<input type="text" class="form-control" id="initialPheromone" placeholder="Initial Pheromone Trails" value="0.1">
							    	</div>
							    </div>
							    <div class="col-sm-6">
							    	<div class="form-group">
								    	<label for="pheromonePersistence">Pheromone Persistence</label>
								    	<input type="text" class="form-control" id="pheromonePersistence" placeholder="Pheromone Persistence" value="0.5">
							    	</div>
							  	</div>
							  	<div class="col-sm-6">
							  		<div class="form-group">
								    	<label for="numAgents">Number of Ants</label>
								    	<input type="text" class="form-control" id="numAgents" placeholder="Number of Ants" value="3">
							    	</div>
							    </div>
							    <div class="col-sm-6">
							    	<div class="form-group">
								    	<label for="maxIterations">Number of Iterations</label>
								    	<input type="text" class="form-control" id="maxIterations" placeholder="Number of Iterations" value="10">
							    	</div>
							  	</div>
							  	<div class="col-sm-6">
							  		<div class="form-group">
								    	<label for="q">Q</label>
								    	<input type="text" class="form-control" id="q" placeholder="Q value" value="1.0">
							    	</div>
							    </div>
							    <div class="col-sm-6">
							    	<div class="form-group">
								    	<label for="strategy">Strategy</label>
								    	<select class="form-control" id="strategy">
											  <option value="ant_cycle">Ant Cycle</option>
											  <option value="ant_density">Ant Density</option>
											  <option value="ant_quantity">Ant Quantity</option>
										</select>
							    	</div>
							  	</div>
					  		</form>
					  	</div>
					</div>
					<!-- Genetic Algorithm -->
					<div id="geneticSettingsPanel" class="panel panel-default settingPanel" style="display: none;">
						<div class="panel-heading">
					    	<h3 class="panel-title">Genetic Algorithm Settings</h3>
					  	</div>
					  	<div class="panel-body">
					  		<form action="">
							  	<div class="col-sm-6">
							  		<div class="form-group">
								    	<label for="populationSize">Population Size</label>
								    	<input type="text" class="form-control" id="populationSize" placeholder="Population Size" value="100">
							    	</div>
							    </div>
							    <div class="col-sm-6">
							    	<div class="form-group">
								    	<label for="maxEvaluations">Maximum of evaluations</label>
								    	<input type="text" class="form-control" id="maxEvaluations" placeholder="Evaluations" value="1000">
							    	</div>
							  	</div>
							  	<div class="col-sm-6">
							  		<div class="form-group">
								    	<label for="crossover">Crossover probability</label>
								    	<input type="text" class="form-control" id="crossover" placeholder="Crossover probability" value="0.9">
							    	</div>
							    </div>
							    <div class="col-sm-6">
							    	<div class="form-group">
								    	<label for="mutation">Mutation probability</label>
								    	<input type="text" class="form-control" id="mutation" placeholder="Mutation probability" value="0.1">
							    	</div>
							  	</div>
					  		</form>
					  	</div>
					</div>
      			</div>
      		</div>
      		<div class="row">
      			<div class="col-md-6">
	      			<div class="panel panel-primary" style="min-height: 150px;">
						<div class="panel-heading">
					    	<h3 id="allItemsLabel" class="panel-title">List with all items</h3>
					  	</div>
					  	<div class="panel-body text-center">
					  		<div id="allItems"><ul class="list-group text-left"><li class="list-group-item"><span class="badge">52</span><strong>Item 1</strong>: [gain= 100, weight=52]</li><li class="list-group-item"><span class="badge">23</span><strong>Item 2</strong>: [gain= 60, weight=23]</li><li class="list-group-item"><span class="badge">35</span><strong>Item 3</strong>: [gain= 70, weight=35]</li><li class="list-group-item"><span class="badge">15</span><strong>Item 4</strong>: [gain= 15, weight=15]</li><li class="list-group-item"><span class="badge">7</span><strong>Item 5</strong>: [gain= 15, weight=7]</li></ul></div>
					  	</div>
					</div>
				</div>
				
				<div class="col-md-6">
	      			<div class="panel panel-primary" style="min-height: 150px;">
						<div class="panel-heading">
					    	<h3 id="labelSelectedItemsPanel" class="panel-title">List with all selected items</h3>
					  	</div>
					  	<div class="panel-body text-center">
					  		<div id="selectedItems"></div>
					  	</div>
					</div>
				</div>
      		</div>
    	</div>
    	<div id="footer">
      		<div class="container">
        		<p class="text-muted">Developed by <a href="http://pedroalmir.com">Pedro Almir</a> © 2014</p>
      		</div>
	    </div>
	</body>
</html>