var grid;

var columns = [ {
	id : "id",
	name : "ID",
	field : "id"
}, {
	id : "date",
	name : "Datum",
	field : "date"
}, {
	id : "stiegl",
	name : "Stiegl Bier",
	field : "stiegl"
}, {
	id : "bergkoenig",
	name : "Bergkönig Bier",
	field : "bergkoenig"
}, {
	id : "radler",
	name : "Gösser Radler",
	field : "radler"
}, {
	id : "cola",
	name : "Coca Cola",
	field : "cola"
}, {
	id : "iceTeaPeach",
	name : "Eistee Pfirsich",
	field : "iceTeaPeach"
}, {
	id : "iceTeaCitron",
	name : "Eistee Zitrone",
	field : "iceTeaCitron"
}, {
	id : "water",
	name : "Mineral 0,5l",
	field : "water"
}, {
	id : "spritzer",
	name : "Weißer Spritzer",
	field : "spritzer"
}, {
	id : "toast",
	name : "Toast",
	field : "toast"
}, {
	id : "pizzaSalami",
	name : "Pizza Salami",
	field : "pizzaSalami"
}, {
	id : "pizzaMargarita",
	name : "Pizza Margarita",
	field : "pizzaMargarita"
}, {
	id : "manner",
	name : "Manner Schnitten",
	field : "manner"
}, {
	id : "pischinger",
	name : "Pischinger Ecken",
	field : "pischinger"
}, {
	id : "chocolate",
	name : "Fairtrade Schoki",
	field : "radlchocolateer"
}

];
var options = {
	editable : true,
	enableCellNavigation : true,
	enableColumnReorder : false
};

var dataView = new Slick.Data.DataView();

grid = new Slick.Grid("#myGrid", dataView, columns, options);

// Make the grid respond to DataView change events.
dataView.onRowCountChanged.subscribe(function(e, args) {
	grid.updateRowCount();
	grid.render();
});

dataView.onRowsChanged.subscribe(function(e, args) {
	grid.invalidateRows(args.rows);
	grid.render();
});

$(function populateGrid() {
    
	$.getJSON('/purchases.json', function(ServerResponse) {
		dataView.setItems(ServerResponse.data);
    })
	
	

	grid.setSelectionModel(new Slick.RowSelectionModel());

	grid.onDblClick.subscribe(function(){
		$('#myModal').modal('show');
	});
	
	grid.onClick.subscribe(function(e, args) {
		var cell = grid.getCellFromEvent(e);

		var item = grid.getDataItem(args.row);

		item['bergkoenig'] = item['bergkoenig'] + 1;
		dataView.updateItem(item['id'], item);
	});
})

$('#newBtn').click(function() {
	$('#myModal').modal('show');
})

$('#saveBtn').click(function() {
	alert("save");
	$('#purchaseForm').submit();
	alert("saved");
})

// Shorthand for $( document ).ready()
$(function() {
	$('#datefield').val($.format.date(new Date(), 'dd-MM-yyyy'));
});

// initialisiert den Datepicker und aktualisiert das Datumsfeld wenn ein neues
// Datum in Datepicker ausgewählt wurde.
$(function() {
	var datepicker = $('#datepicker').datepicker({
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#datefield').val($.format.date(ev.date, 'dd-MM-yyyy'));
	});
})

// aktualisiert den DatePicker wenn in Feld ein neues Datum eingegeben wurde.
$('#datefield')
		.change(
				function() {
					var dateddMMYYYYRegex = /(^(((0[1-9]|[12][0-8])[-](0[1-9]|1[012]))|((29|30|31)[-](0[13578]|1[02]))|((29|30)[-](0[4,6,9]|11)))[-](19|[2-9][0-9])\d\d$)|(^29[-]02[-](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;

					if (dateddMMYYYYRegex.test($('#datefield').val())) {
						var dateStr = $("#datefield").val().split("-");
						date = new Date(dateStr[2], dateStr[1] - 1, dateStr[0]);
						$('#datepicker').datepicker('setValue', date);
					}
				});

// Form Validator
$('.form').bootstrapValidator({
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		stieglInput : {
			message : 'The stieglInput is not valid',
			validators : {
				notEmpty : {
					message : 'The stieglInput is required and cannot be empty'
				},
				regexp : {
					regexp : /^[0-9]\d*$/,
					message : 'Nur ganze Zahlen erlaubt'
				}
			}
		},
		stieglPrice : {
			validators : {
				notEmpty : {
					message : 'The stieglPrice is required and cannot be empty'
				},
				regexp : {
					regexp : /[0-9]+(\.[0-9][0-9]?)?/,
					message : 'Nur Zahlen erlaubt'
				}
			}
		}
	}
});

$(".input-group").change(function() {
	$('.form').data('bootstrapValidator').validate();
});