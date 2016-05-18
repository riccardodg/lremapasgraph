/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Riccardo Del Gratta &lt;riccardo.delgratta@ilc.cnr.it&gt;
 * Created: May 18, 2016
 */
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<HTML>
<HEAD>
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
<script>
function getparams() {
  // This function is anonymous, is executed immediately and 
  // the return value is assigned to QueryString!
  var query_string = {};
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i=0;i<vars.length;i++) {
    var pair = vars[i].split("=");
        // If first entry with this name
    if (typeof query_string[pair[0]] === "undefined") {
      query_string[pair[0]] = decodeURIComponent(pair[1]);
        // If second entry with this name
    } else if (typeof query_string[pair[0]] === "string") {
      var arr = [ query_string[pair[0]],decodeURIComponent(pair[1]) ];
      query_string[pair[0]] = arr;
        // If third or later entry with this name
    } else {
      query_string[pair[0]].push(decodeURIComponent(pair[1]));
    }
  }
  return query_string;
}

</script>

<script type='text/javascript' src="http://labratrevenge.com/d3-tip/javascripts/d3.tip.v0.6.3.js"> </script>
<style>

.link {
  stroke: #999;
  stroke-opacity: .1;
  stroke-width: 4px;
}



.node circle {
  stroke: #999;
  stroke-width: 1.5px;
}

.node text {
  font: 15px sans-serif;
  pointer-events: none;
}

.d3-tip {
    line-height: 1;
    color: black;
}

</style>
</HEAD>
<body>
<center>
<div id="title">
<h1>Author 2 Author connections via same used Resources: DEMOMAP RESOURCES -</h1>
</div>
<p>
</p>
<div id="area1" style="border:1px solid black;">
</div>
</center>
<script>
var QueryString = getparams();
alert (QueryString.author);
var logos = {
  tool: "M26.834,14.693c1.816-2.088,2.181-4.938,1.193-7.334l-3.646,4.252l-3.594-0.699L19.596,7.45l3.637-4.242c-2.502-0.63-5.258,0.13-7.066,2.21c-1.907,2.193-2.219,5.229-1.039,7.693L5.624,24.04c-1.011,1.162-0.888,2.924,0.274,3.935c1.162,1.01,2.924,0.888,3.935-0.274l9.493-10.918C21.939,17.625,24.918,16.896,26.834,14.693z",
  resource: "M15.5,3.029l-10.8,6.235L4.7,21.735L15.5,27.971l10.8-6.235V9.265L15.5,3.029zM24.988,10.599L16,15.789v10.378c0,0.275-0.225,0.5-0.5,0.5s-0.5-0.225-0.5-0.5V15.786l-8.987-5.188c-0.239-0.138-0.321-0.444-0.183-0.683c0.138-0.238,0.444-0.321,0.683-0.183l8.988,5.189l8.988-5.189c0.238-0.138,0.545-0.055,0.684,0.184C25.309,10.155,25.227,10.461,24.988,10.599z",
  people: "M21.021,16.349c-0.611-1.104-1.359-1.998-2.109-2.623c-0.875,0.641-1.941,1.031-3.103,1.031c-1.164,0-2.231-0.391-3.105-1.031c-0.75,0.625-1.498,1.519-2.111,2.623c-1.422,2.563-1.578,5.192-0.35,5.874c0.55,0.307,1.127,0.078,1.723-0.496c-0.105,0.582-0.166,1.213-0.166,1.873c0,2.932,1.139,5.307,2.543,5.307c0.846,0,1.265-0.865,1.466-2.189c0.201,1.324,0.62,2.189,1.463,2.189c1.406,0,2.545-2.375,2.545-5.307c0-0.66-0.061-1.291-0.168-1.873c0.598,0.574,1.174,0.803,1.725,0.496C22.602,21.541,22.443,18.912,21.021,16.349zM15.808,13.757c2.362,0,4.278-1.916,4.278-4.279s-1.916-4.279-4.278-4.279c-2.363,0-4.28,1.916-4.28,4.279S13.445,13.757,15.808,13.757z"

};

var groupLogo = {
        0: logos.people,
        1: logos.resource
}

var width = 2000,
    height = 2000;

//var color = d3.scale.category20();

var color = d3.scale.ordinal()

         .range(["#3cb371","#008b8b","#9acd32","#20b2aa","#87cefa","#90ee90","#98fb98","#4169e1","#778899","#800080","#add8e6","#afeeee","#b0c4de","#b0e0e6","#c0c0c0","#d2b48c","#d3d3d3","#db7093","#ff0000","#0000ff","#ffff00","#e9967a","#eee8aa","#f0e68c","#f0f8ff","#f08080","#faebd7","#fafad2","#ff7f50","#ffa07a","#ffa500","#ffb6c1","#ffe4c4","#ffffe0",]);

var radius = d3.scale.sqrt()
    .range([0, 6]);

var svg = d3.select("#area1").append("svg")
    .attr("width", width)
    .attr("height", height);

var tip = d3.tip()
    .attr('class', 'd3-tip')
        .offset([-10, 0])
        .html(function (d) { if (d.size==10) return   d.affi; else return d.atom;
})
svg.call(tip);

var force = d3.layout.force()
    .size([width, height])
    .charge(-700)
    //.charge(function(d){ var a=d.source.size; alert (a);return -700*a;})
    .linkDistance(80)
    .linkStrength(0.1);
    //.linkDistance(function(d) { return radius(d.source.size) + radius(d.target.size) + 20; });

d3.json("output.json", function(error, graph) {
  if (error) throw error;

  force
      .nodes(graph.nodes)
      .links(graph.links)
      .on("tick", tick)
      .start();

  var link = svg.selectAll(".link")
      .data(graph.links)
    .enter().append("g")
      .attr("class", "link");

  link.append("line")
//     .style("stroke-width", function(d) { return (d.bond * 2 - 1) * 2 + "px"; });

  link.filter(function(d) { return d.bond >= 1; }).append("line")
      .attr("class", "separator");

  var node = svg.selectAll(".node")
      .data(graph.nodes)
    .enter().append("g")
      .attr("class", "node")
      .call(force.drag)
          .on('mouseover', tip.show) //Added
          .on('mouseout', tip.hide); //Added



        node.append("path")
        //.attr("r", function(d) { return radius(d.size); })
        .attr("d", function(d) {
                 if (d.group in groupLogo) return groupLogo[d.group];
                 return logos.people;
                })
        .style("fill", function(d) { return color(d.affi); });

  node.append("text")
      .attr("dy", ".35em")
      .attr("text-anchor", "top")
      .text(function(d) { if (d.size==10) return d.atom; });

  function tick() {
    link.selectAll("line")
        .attr("x1", function(d) { return d.source.x; })
        .attr("y1", function(d) { return d.source.y; })
        .attr("x2", function(d) { return d.target.x; })
        .attr("y2", function(d) { return d.target.y; });

    node.attr("transform", function(d) { return "translate(" + (d.x - 15)  + "," + (d.y -15) + ")"; });
  }
});
</script>



