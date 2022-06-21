// Állapot viselkedési tervezési minta
// média lejátszó
// két gomb
// 4 állapot
// a két gomb viselkedése más és más lesz a 4 belső állapottól függően
// lesz egy: Állapot
// Play gomb
// Audió forrás gomb
// Állapotváltozások:
// Állapotok: készenlét, mp3 lejátszás, mp3 megállítás, rádió hallgatás
// Lejátszás: stop-paused, start-play, next station
// Audió forr: mp3 play, rádió play, rádió play, készenlét
class AudioPlayer
{
    private AudioPlayerState state; // ebben tároljuk a belső állapotot
    public AudioPlayerState GetState() {
        return state;
    }
    public void SetState(AudioPlayerState state) {
        this.state = state;
    }
    public AudioPlayer(AudioPlayerState state) {
        this.state = state;
    }
    public void PressPlay() {
        state.PressPlay(this);
    }
    public void PressAudioSource() {
        state.PressAudioSource(this);
    }
}
abstract class AudioPlayerState // állapot reprezentálása
{
    // a két gomblenyomása
    public abstract void PressPlay(AudioPlayer player);
    public abstract void PressAudioSource(AudioPlayer player);
}
class StandbyState extends AudioPlayerState { // készenléti állapot

    public StandbyState() {
        System.out.println("StandBy");
    }
    @Override
    public void PressPlay(AudioPlayer player)
    {
        System.out.println("Play pressed: no effect");
    }
    @Override
    public void PressAudioSource(AudioPlayer player)
    {
        player.SetState(new MP3PlayingState());
    }
}
class MP3PlayingState extends AudioPlayerState { // mp3 hallgatás állapot
    public MP3PlayingState() {
        System.out.println("Playing MP3");
    }
    @Override
    public  void PressPlay(AudioPlayer player)
    {
        player.SetState(new MP3PausedState());
    }
    @Override
    public void PressAudioSource(AudioPlayer player)
    {
        player.SetState(new RadioState());
    }
}
class MP3PausedState extends AudioPlayerState { // a megállított mp3 állapot
    public MP3PausedState() {
        System.out.println("Paused MP3");
    }
    @Override
    public void PressPlay(AudioPlayer player)
    {
        player.SetState(new MP3PlayingState());
    }
    @Override
    public void PressAudioSource(AudioPlayer player)
    {
        player.SetState(new RadioState());
    }
}
class RadioState extends AudioPlayerState { // a rádió állapot
    public RadioState() {
        System.out.println("Playing Radio");
    }
    @Override
    public void PressPlay(AudioPlayer player)
    {
        System.out.println("Switch to next Station");
    }
    @Override
    public void PressAudioSource(AudioPlayer player)
    {
        player.SetState(new StandbyState());
    }
}
public class Main {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer(new StandbyState());
        player.PressPlay();
        player.PressAudioSource();
        player.PressPlay();
        player.PressPlay();
        player.PressAudioSource();
        player.PressPlay();
        player.PressAudioSource();
    }
}