import { Component, AfterViewInit } from '@angular/core';
import * as jQuery from 'jquery';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  title = 'frontend';

  ngAfterViewInit() {
    (function($) {
      "use strict";

      var path = location.pathname;

      // Add active state to sidbar nav links
      $('#layoutSidenav_nav .sb-sidenav a.nav-link').each((index:any, element:any) => {
        if ($(element).attr('href') === path) {
            $(element).addClass("active");
        }
    });

      // Toggle the side navigation
      $("#sidebarToggle").on("click", function (event: { preventDefault: () => void; }) {
        $("body").toggleClass("sb-sidenav-toggled");
    });

    })(jQuery);
  }
}
