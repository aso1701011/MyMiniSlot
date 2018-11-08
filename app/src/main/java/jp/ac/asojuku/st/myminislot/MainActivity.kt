package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var bet:Int=0
    var coin:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_reset.setOnClickListener{betMoney(0,bet,coin)}
        bet_up.setOnClickListener{betMoney(1,bet,coin)}
        bet_down.setOnClickListener{betMoney(2,bet,coin)}
        start.setOnClickListener{start(coin,bet)}
    }



    fun betMoney(int:Int,bet:Int,coin:Int){
        if(int==0){
            this.bet = 0
            this.coin = 1000
        }else if(int==1){
            if(coin>0) {
                this.bet += 10
            }
        } else if(int==2){
            if(bet>10) {
                this.bet -= 10
            }
        }else if(int==3){
            this.bet = bet
            this.coin = coin
            if(bet>coin){
                this.bet = this.coin
            }
        }
        BetCoin.setText(Integer.toString(this.bet))
        MyCoin.setText(Integer.toString(this.coin))
    }
    fun start(coin:Int,bet:Int){
        this.coin-=this.bet
        MyCoin.setText(Integer.toString(this.coin))
        val intent= Intent(this,GameActivity::class.java)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit().putInt("coin",this.coin).putInt("bet",bet).commit()
        startActivity(intent)
    }

    override fun onResume(){
        super.onResume()
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        betMoney(3,pref.getInt("bet",20),pref.getInt("coin",1000))
    }


}
