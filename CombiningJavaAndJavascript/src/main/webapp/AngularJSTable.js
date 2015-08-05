angular.module('AutoApp', [])
    .controller('AutoController', function() {
  var controller=this;
  controller.dataSet = [
    {hersteller:'VW', typ:'Golf', baujahr:2008},
    {hersteller:'Honda', typ:'Civic', baujahr:2012},
    {hersteller:'BMW', typ:'320i', baujahr:2014}];
  controller.neuesFahrzeug = function() {
    var fahrzeug = {hersteller:controller.hersteller, 
                    typ:controller.typ,
                     baujahr:controller.baujahr};
    controller.dataSet.push(fahrzeug);
  }
});
