import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DiceService {

  constructor(private http: HttpClient) { }

  rollDice(dice: string) {
    return this.http
    .get(`http://aleajactarest.herokuapp.com/api/dices/roll/${dice}`);
  }
}
