////
////$(document).ready( function () {
////$.noConflict();
////	var table = $('#bookTable').DataTable();
////	$.ajax({
////                   url: '/api/books',
////                   type: 'get',
////                   dataType: 'json',
////                   success: function(data) {
////                       for(var i=0;i<data.length;i++)
////                       {
////                           table.row.add( [
////                               data[i].id,
////                                data[i].title,
////                               data[i].author,
////                               data[i].description.substring(0, 18)+"...",
////                               data[i].dayCreate,
////                               data[i].dayUpdate
////                           ] )
////                       }
////                       table.draw();
////                   }
////               });
////	});
//
//$(document).ready( function () {
//	var table = $('#bookTableHomePage').DataTable();
//	$.ajax({
//                   url: '/api/books',
//                   type: 'get',
//                   dataType: 'json',
//                   success: function(data) {
//                       for(var i=0;i<data.length;i++)
//                       {
//                           table.row.add( [
//                               data[i].id,
//                                data[i].title,
//                               data[i].author,
//                               data[i].description.substring(0, 18)+"...",
//                               data[i].dayCreate,
//                               data[i].dayUpdate
//                           ] )
//                       }
//                       table.draw();
//                   }
//               });
//	});
//
//$(document).ready( function () {
//	var table = $('#bookTableAdmin').DataTable();
//	$.ajax({
//                   url: '/api/books',
//                   type: 'get',
//                   dataType: 'json',
//                   success: function(data) {
//                       for(var i=0;i<data.length;i++)
//                       {
//                           table.row.add( [
//                               data[i].id,
//                                data[i].title,
//                               data[i].author,
//                               data[i].description.substring(0, 18)+"...",
//                               data[i].dayCreate,
//                               data[i].dayUpdate
//                           ] )
//                       }
//                       table.draw();
//                   }
//               });
//	});
//
//$(document).ready( function () {
//
//	 var table = $('#bookTable').DataTable();
//        $('#bookTable tbody').on('click', 'tr', function () {
//            var data = table.row( this ).data();
//            window.location = "/book/"+data[0];
//        } );
//	});
//
////$(document).ready( function () {
////	 var table = $('#myBook').DataTable();
////        $('#myBook tbody').on('click', 'tr', function () {
////            var data = table.row( this ).data();
////            window.location = "/bookUser/"+data[0];
////        } );
////	});
//
//$(document).ready( function () {
//	 var table = $('#bookTableAdmin').DataTable();
//        $('#bookTableAdmin tbody').on('click', 'tr', function () {
//            var data = table.row( this ).data();
//            window.location = "/bookAdmin/"+data[0];
//        } );
//	});
//
//$(document).ready( function () {
//	 var table = $('#bookTableHomePage').DataTable();
//        $('#bookTableHomePage tbody').on('click', 'tr', function () {
//            var data = table.row( this ).data();
//            window.location = "/bookUser/"+data[0];
//        } );
//	});
