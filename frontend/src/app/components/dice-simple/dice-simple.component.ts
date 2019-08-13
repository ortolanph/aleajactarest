import { Component, OnInit } from '@angular/core';
import { DiceService } from 'src/app/services/dice.service';

@Component({
  selector: 'app-dice-simple',
  templateUrl: './dice-simple.component.html',
  styleUrls: ['./dice-simple.component.scss']
})
export class DiceSimpleComponent implements OnInit {

  simpleResult: any;

  constructor(private diceService: DiceService) { }

  ngOnInit() {
  }

  rollDice(dice: string) {  
    this.diceService
    .rollDice(dice)
    .subscribe(
      (res) => {
        this.simpleResult = res;
      }
    );
  }

}
