import { animate, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  animations: [
    trigger('slideInOut', [
      transition(':enter', [style({ opacity: 0 }), animate(600)]),
      transition(':leave', [animate(600, style({ opacity: 0 }))])
    ])
  ]
})
export class RegistrationComponent implements OnInit {

  visible: boolean;

  constructor() { }

  ngOnInit(): void {
    this.visible = false;
  }

}
