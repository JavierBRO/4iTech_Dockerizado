import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Room } from '../models/room.model';

@Component({
  selector: 'app-room-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './room-form.component.html',
  styleUrl: './room-form.component.css'
})
export class RoomFormComponent implements OnInit{
  
  roomForm = new FormGroup({
    id: new FormControl<number>(0),
    name: new FormControl<string>(''),
    capacity: new FormControl<number>(0),
    hasSockets: new FormControl<boolean>(false),
    photoUrl: new FormControl<string>('')
  });
  
  isUpdate: boolean = false;

  room: Room | undefined;
  photoFile: File | undefined;
  photoPreview: string | undefined;

  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient,
    private router: Router,
    private activatedRoute: ActivatedRoute){}
  
  ngOnInit(): void {
    
    this.activatedRoute.params.subscribe(params => {
      const id = params['id'];
      if (!id) return;

      this.httpClient.get<Room>('http://localhost:8080/rooms/' + id)
      .subscribe(roomFromBackend => {
        this.roomForm.reset(roomFromBackend);
        this.isUpdate = true;
      });
    });
  }
  onFileChange(event: Event) {
    console.log(event);
    let target = event.target as HTMLInputElement;

    if(target.files === null || target.files.length == 0) {
      return; // no se procesa ningún archivo
    }

    this.photoFile = target.files[0]; // guardar el archivo para enviarlo luego en el save()

  // OPCIONAL: PREVISUALIZAR LA IMAGEN POR PANTALLA
  let reader = new FileReader();
  reader.onload = event => this.photoPreview = reader.result as string;
  reader.readAsDataURL(this.photoFile);
  }

  save(){
    // const room: Room = this.roomForm.value as Room;
    // console.log(room);
    let formData = new FormData();

    formData.append('id', this.roomForm.get('id')?.value?.toString() ?? '0');
    formData.append('name', this.roomForm.get('name')?.value ?? '');
    formData.append('capacity', this.roomForm.get('capacity')?.value?.toString() ?? '0');
    formData.append('hasSockets', this.roomForm.get('hasSockets')?.value?.toString() ?? '0');
    formData.append('photoUrl', this.roomForm.get('photoUrl')?.value ?? '');

    if(this.photoFile) {
      formData.append("photo", this.photoFile);
    }



    if (this.isUpdate){
      const url = 'http://localhost:8080/rooms/' + this.room?.id;
      this.httpClient.put<Room>(url, formData).subscribe(roomFromBackend => {
        this.navigateToList();
      });
    } else {
      const url = 'http://localhost:8080/rooms';
      this.httpClient.post<Room>(url, formData).subscribe(roomFromBackend => {
        this.navigateToList();
      });
    }
  }
  private navigateToList() {
    this.router.navigate(['/rooms']);
  }
}
