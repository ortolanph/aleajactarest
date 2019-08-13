import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { DiceSimpleComponent } from './components/dice-simple/dice-simple.component';
import { DiceNotationComponent } from './components/dice-notation/dice-notation.component';
import { DiceCupComponent } from './components/dice-cup/dice-cup.component';
import { DiceCustomComponent } from './components/dice-custom/dice-custom.component';


const   routes: Routes = [
  { path: '', component: AboutComponent },
  { path: 'simple', component: DiceSimpleComponent },
  { path: 'notation', component: DiceNotationComponent },
  { path: 'cup', component: DiceCupComponent },
  { path: 'custom', component: DiceCustomComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


