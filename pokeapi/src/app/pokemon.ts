import { Type } from './type';
import { Stat } from './stat';
import { Sprites } from './sprites';

export class Pokemon {
  id!: number;
  name!: string;
  height!: number;
  weight!: number;
  types!: Type[];
  stats?: Stat[];
  sprites?: Sprites;
}
