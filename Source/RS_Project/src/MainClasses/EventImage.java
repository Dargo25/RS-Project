/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import javafx.scene.image.ImageView;

/**
 *
 * @author Samosad
 */
public class EventImage {
    ImageView userImg;
    int number;
    
    public void SetImg(ImageView img)
    {
        userImg = img;
    }
    
    public void SetNumber(int numb)
    {
        number = numb;
    }
    
    public ImageView GetImg(){
        return userImg;
    }
    
    public int GetNumber(){
        return number;
    }
}
