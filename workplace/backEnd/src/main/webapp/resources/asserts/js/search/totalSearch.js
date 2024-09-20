totalSearchFlag = false;






function pressSearch(){ 
	
	
	if(window.event.keyCode == 13){
		
			
		submitTotal();
		
	}
}

	





function submitTotal(){
	

	 var totalSearchInput = document.getElementById('totalSearchInput');
	

	 var resultDate = (totalSearchInput.value);
	 location.href = "/search/result?resultData="+resultDate;
	 
	 
	
	
	
	
	
}





function totalAutocomplete(inp, arr) {
	
  var currentFocus;
  inp.addEventListener("input", function (e) {


        var a, b, i;
        val = this.value;
   
        closeAllLists();
               
         if (!val) { return false; }
        var seokCnt = 0;
        currentFocus = -1;
               
        a = document.createElement("DIV");
        a.setAttribute("id", this.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items overflow-auto");
   
        this.parentNode.appendChild(a);
                           
        for (i = 0; i < arr.length; i++) {

          if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
            
            seokCnt = seokCnt+1;


            if(seokCnt>=5){
              a.setAttribute("style", "height:200px;");
        }else{
          a.style="";
        }

        b = document.createElement("DIV");
       
        b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
        b.innerHTML += arr[i].substr(val.length);
       
        b.innerHTML += "<input type='hidden' id='autototalSearch' name='autoSearch' value='" + arr[i] + "'>";
       
        b.addEventListener("click", function (e) {
         
          inp.value = this.getElementsByTagName("input")[0].value;
        
          alert(inp.value);
          
           location.href = "/search/result?resultData="+inp.value;
           e.stopPropagation();
           

        
          closeAllLists();
        });
        a.appendChild(b);
      }


    }
    
    if(seokCnt==0){
        b = document.createElement("DIV");
        b.innerHTML = "<h5>연관 검색어가 없습니다.</h5>";
        a.appendChild(b);
      }

  });
  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function (e) {
    var x = document.getElementById(this.id + "autocomplete-list");
    if (x) x = x.getElementsByTagName("div");
    if (e.keyCode == 40) {
      /*If the arrow DOWN key is pressed,
      increase the currentFocus variable:*/
      currentFocus++;
      /*and and make the current item more visible:*/
      addActive(x);
    } else if (e.keyCode == 38) { //up
      /*If the arrow UP key is pressed,
      decrease the currentFocus variable:*/
      currentFocus--;
      /*and and make the current item more visible:*/
      addActive(x);
    } else if (e.keyCode == 13) {
      /*If the ENTER key is pressed, prevent the form from being submitted,*/
      e.preventDefault();
      if (currentFocus > -1) {
        /*and simulate a click on the "active" item:*/
        if (x) x[currentFocus].click();
      }
    }
  });

  function addActive(x) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /*add class "autocomplete-active":*/
    x[currentFocus].classList.add("autocomplete-active");
  }

  function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }

  function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }

 
    document.addEventListener("click", function (e) {
    /*document.getElementById("totalSearchInput").value = "";*/
    closeAllLists(e.target);

  });
}


var proAndNick = new Array();
	

$.ajax({
    url: "/search/data.do",
    dataType: "json", 
    method: "POST",
    success: function(result) {
    	
       
    	for (var i = 0; i < result.length; i++) { 
    		
    		 proAndNick[i]  = result[i];
    		
    	}
    	
    	
    }
});



window.addEventListener('load', function(){


	
	
	/*$('.btn-search:focus').on('submit', function () {
		
		 var totalSearchInput = document.getElementById('totalSearchInput');
			
		 alert(totalSearchInput.value);
		 var resultDate = (totalSearchInput.value);
		 location.href = "/search/result?resultData="+resultDate;
		
	});*/
	
	
	
	totalAutocomplete(document.getElementById("totalSearchInput"), proAndNick);


});








