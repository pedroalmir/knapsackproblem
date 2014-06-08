/** Data definition */
var selectedAlgorithm = null;

/** Parse data to JSON */
function parseJSON(data) {
    return window.JSON && window.JSON.parse ? window.JSON.parse(data) : (new Function("return " + data))(); 
}

/** Update list of items */
function updateListOfItems(items){
	$('#listOfItems').val(JSON.stringify(items));
	
	var html = '<ul class="list-group text-left">';
	for(var i in items){
		html += '<li class="list-group-item">';
		html += '<span class="badge">' + items[i].weight + '</span>';
		html += '<strong>Item ' + items[i].id + '</strong>: [gain= ' + items[i].gain + ', weight=' + items[i].weight + ']';
		html += '</li>';
	}
	html += '</ul>';
	$('#allItems').html(html);
}

/** Update list of select items */
function updateListOfItems(items, selectedItemsMap){
	var html = '<ul class="list-group text-left">';
	for(var i in items){
		if(selectedItemsMap[i]){
			html += '<li class="list-group-item list-group-item-success">';
		}else{
			html += '<li class="list-group-item list-group-item-danger">';
		}
		html += '<span class="badge">' + items[i].weight + '</span>';
		html += '<strong>Item ' + items[i].id + '</strong>: [gain= ' + items[i].gain + ', weight=' + items[i].weight + ']';
		html += '</li>';
	}
	html += '</ul>';
	$('#selectedItems').html(html);
}

/** Send request */
function sendRequest(json, items){
	$.ajax({
		url: '/',
		type: 'POST',
		data: {json: json},
		beforeSend: function(){
			/* Active the loader */
			$('#execute').button('loading');
		}, success: function(data, textStatus, jqXHR){
			var result = parseJSON(data);
			console.log(result);
			
			var selectedItemsMap = new Object();
			$.map(result.selectedItems, function(value, key){
				selectedItemsMap[value.id] = value;
			});
			
			updateListOfItems(items, selectedItemsMap);
			/* Atualizando o label do panel de controle */
			$('#labelSelectedItemsPanel').text('KnapsackSolution [Fitness=' + result.fitness + ', accumulatedWeight=' + result.accumulatedWeight + ']');
			
		}, error: function(jqXHR, textStatus, errorThrown){
			console.log(textStatus);
		}, complete: function(jqXHR, textStatus){
			/* Disable the loader*/
			$('#execute').button('reset')
		}
	});
}

$(document).ready(function() {
	/* Algorithm change action*/
	$('#algorithm').change(function(){
		var choosed = $(this).val();
		selectedAlgorithm = choosed;
		
		if(choosed == 'aco'){
			$('.settingPanel').fadeOut("fast","swing", function(){
				$('#antSettingsPanel').show("slow","swing");
			});
			
			$('#listOfItems').attr('rows', 13);
		}else if(choosed == 'genetic'){
			$('.settingPanel').fadeOut("fast","swing", function(){
				$('#geneticSettingsPanel').show("slow","swing");
			});
			
			$('#listOfItems').attr('rows', 6);
		}else{
			$('.settingPanel').fadeOut("slow","swing");
			$('#listOfItems').attr('rows', 5);
		}
	});
	
	$('#listOfItems').change(function(){
		updateListOfItems(parseJSON($(this).val()));
	});
	
	$('#addItem').click(function(){
		var items = parseJSON($('#listOfItems').val());
		var weightU = parseJSON($('#weight').val());
		var gainU = parseJSON($('#gain').val());
		
		for(var i in items){
			items[i].id = parseInt(i)+1;
		}
		
		if(weight != null && gain != null){
			items.push({id: items.length+1, weight: weightU, gain: gainU});
		}
		updateListOfItems(items);
	});
	
	$('#execute').click(function(){
		var btn = $(this);
		var choosed = $('#algorithm').val();
		if(choosed == 'brute_force'){
			/** List of items */
			var items = parseJSON($('#listOfItems').val());
			/** Knapsack restriction */
			var restriction = $('#restriction').val();
			
			/** JSON */
			var json = {algorithm: choosed, restriction: restriction, items: items};
			json = JSON.stringify(json);
			
			//console.log(json);
			sendRequest(json, items);
		}else if(choosed == 'greedy'){
			/** List of items */
			var items = parseJSON($('#listOfItems').val());
			/** Knapsack restriction */
			var restriction = $('#restriction').val();
			
			/** JSON */
			var json = {algorithm: choosed, restriction: restriction, items: items};
			json = JSON.stringify(json);
			
			//console.log(json);
			sendRequest(json, items);
		}else if(choosed == 'aco'){
			/** Ant Colony Optimization Settings */
			var alpha = $('#alpha').val();
			var beta = $('#beta').val();
			var initialPheromone = $('#initialPheromone').val();
			var pheromonePersistence = $('#pheromonePersistence').val();
			var numAgents = $('#numAgents').val();
			var maxIterations = $('#maxIterations').val();
			var q = $('#q').val();
			var strategy = $('#strategy').val();
			
			/** List of items */
			var items = parseJSON($('#listOfItems').val());
			/** Knapsack restriction */
			var restriction = $('#restriction').val();
			
			/** JSON */
			var json = {algorithm: choosed, restriction: restriction, items: items,
					alpha: alpha, beta: beta, initialPheromone: initialPheromone, 
					pheromonePersistence: pheromonePersistence, numAgents: numAgents, 
					maxIterations: maxIterations, q: q, strategy: strategy};
			json = JSON.stringify(json);
			
			//console.log(json);
			sendRequest(json, items);
		}else if(choosed == 'genetic'){
			/** Genetic algorithm settings */
			var populationSize = $('#populationSize').val();
			var maxEvaluations = $('#maxEvaluations').val();
			var crossover = $('#crossover').val();
			var mutation = $('#mutation').val();
			
			/** List of items */
			var items = parseJSON($('#listOfItems').val());
			/** Knapsack restriction */
			var restriction = $('#restriction').val();
			
			/** JSON */
			var json = {algorithm: choosed, restriction: restriction, items: items, 
					populationSize: populationSize, maxEvaluations: maxEvaluations, 
					crossover: crossover, mutation: mutation};
			json = JSON.stringify(json);
			
			//console.log(json);
			sendRequest(json, items);
		}
	});
});