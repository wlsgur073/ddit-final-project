$(document).ready(function () {
	var customerTemplate = '<span class="k-state-default" style="background-image: url(\'../content/web/Customers/#:data.CustomerID#.jpg\')"></span>' +
			'<span class="k-state-default"><h3>#: data.ContactName #</h3></span>';
	$("#optional").kendoListBox({
			dataTextField: "ContactName",
			dataValueField: "CustomerID",
			template: customerTemplate,
			dataSource: {

				// data: [
				// 	{ ContactName: "프로그램" },
				// 	{ ContactName: "자바" },
				// 	{ ContactName: "자바칩" },
				// 	{ ContactName: "자바칩프라푸치노" },
				// 	{ ContactName: "자바밥" }
				// ]
				// template: "<div>#:ContactName#</div>",


					transport: {
							read: {
									dataType: "jsonp",
									url: "https://demos.telerik.com/kendo-ui/service/Customers",
							}
					}
			},
			draggable: { placeholder: customPlaceholder },
			dropSources: ["selected"],
			connectWith: "selected",
			toolbar: {
					position: "right",
					tools: ["transferTo", "transferFrom", "transferAllTo", "transferAllFrom"]
			}
	});

	$("#selected").kendoListBox({
			dataTextField: "ContactName",
			dataValueField: "CustomerID",
			template: customerTemplate,
			draggable: { placeholder: customPlaceholder },
			dropSources: ["optional"],
			connectWith: "optional"
	});

	function customPlaceholder(draggedItem) {
			return draggedItem
							.clone()
							.addClass("custom-placeholder")
							.removeClass("k-ghost");
	}
});
/* ===== Logic for creating fake Select Boxes ===== */
$('.sel').each(function() {
  $(this).children('select').css('display', 'none');
  
  var $current = $(this);
  
  $(this).find('option').each(function(i) {
    if (i == 0) {
      $current.prepend($('<div>', {
        class: $current.attr('class').replace(/sel/g, 'sel__box')
      }));
      
      var placeholder = $(this).text();
      $current.prepend($('<span>', {
        class: $current.attr('class').replace(/sel/g, 'text-truncate sel__placeholder'),
        text: placeholder,
        'data-placeholder': placeholder
      }));
      
			
      return;
    }
    
    $current.children('div').append($('<span>', {
			class: $current.attr('class').replace(/sel/g, 'sel__box__options'),
      text: $(this).text()
    }));
  });
});

// Toggling the `.active` state on the `.sel`.
$('.sel').click(function() {
  $(this).toggleClass('active');
});

// Toggling the `.selected` state on the options.
$('.sel__box__options').click(function() {
  var txt = $(this).text();
  var index = $(this).index();
  
  $(this).siblings('.sel__box__options').removeClass('selected');
  $(this).addClass('selected');
  
  var $currentSel = $(this).closest('.sel');
  $currentSel.children('.sel__placeholder').text(txt);
  $currentSel.children('select').prop('selectedIndex', index + 1);
});
