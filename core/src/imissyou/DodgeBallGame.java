package imissyou;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;

public class DodgeBallGame extends Game {
    private Screen screen;
    private Game game;
    private OrthographicCamera camera;
    private Stage stage;
    private BitmapFont font;
    private Skin btnSkin;
    private TextButton logBtn;
    private TextButton.TextButtonStyle textButtonStyle;

    public DodgeBallGame(){
        game = this;
    }
    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        font = new BitmapFont();
        btnSkin = new Skin(Gdx.files.internal("assets/skin/pixthulhu-ui.json"));

        logBtn = new TextButton("Log In", btnSkin);
        logBtn.setSize(500,60);
        logBtn.setPosition(0,0);
        logBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed button");
                return true;
            }
        });

        stage.addActor(logBtn);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void render(){
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}
