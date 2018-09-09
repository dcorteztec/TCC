import { Pipe, PipeTransform, Injectable } from '@angular/core';

@Pipe({
  name: 'filterPipe'
})
@Injectable()
export class FilterPipePipe implements PipeTransform {

  transform(items: any[], field: any, value: string): any[] {
    if (!items) {
        return [];
    }
    if (!field || !value) {
        return items;
    }

    return items.filter(singleItem =>
        singleItem[field].toLowerCase().includes(value.toLowerCase())
    );
}

}
