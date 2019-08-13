import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigatorComponent } from './components/navigator/navigator.component';
import { DiceSimpleComponent } from './components/dice-simple/dice-simple.component';
import { DiceNotationComponent } from './components/dice-notation/dice-notation.component';
import { DiceCupComponent } from './components/dice-cup/dice-cup.component';
import { DiceCustomComponent } from './components/dice-custom/dice-custom.component';
import { AboutComponent } from './components/about/about.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigatorComponent,
    DiceSimpleComponent,
    DiceNotationComponent,
    DiceCupComponent,
    DiceCustomComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
