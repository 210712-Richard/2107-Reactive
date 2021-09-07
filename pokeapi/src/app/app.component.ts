import { Component, OnInit } from '@angular/core';
import { Pokemon } from './pokemon';
import { PokemonService } from './pokemon.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public title = 'https://www.google.com';
  public pokemon?: Pokemon;
  public id = 1;
  constructor(private pokeService: PokemonService) {}

  ngOnInit() {
    // A method that will run when the component is created.
    this.pokeService.getPokemon(this.id).subscribe(poke => {
      this.pokemon = poke;
    });
  }

  submit() {
    console.log(this.id);
    this.pokeService.getPokemon(this.id).subscribe(poke => {
      this.pokemon = poke;
    });
  }
}
