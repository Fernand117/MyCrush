<?php

namespace App\models;

use Illuminate\Database\Eloquent\Model;

class Perfil extends Model
{
    public $fillabel = [
        'nombre','apellidop','apellidom','genero','edad','foto_perfil','foto_portada'
    ];
}
