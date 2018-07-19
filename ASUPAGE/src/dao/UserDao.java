package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBconnector.DBConnector;
import beans.UserBean;
import users.DeleteForm;
import users.NewForm;
import users.UpdateForm;
import users.UserViewForm;

public class UserDao {

	//検索・全レコード取り出し
	public List<UserBean> getUserAll(UserViewForm form) {
		DBConnector db = new DBConnector();
		List<UserBean> users = null;
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		ResultSet rs = null;
		try {
			String sql = "select * from(SELECT USER_ID,FIRST_NAME,LAST_NAME,FIRST_NAME_KANA,LAST_NAME_KANA,TITLE,SEX,CLASSIFICATION1,CLASSIFICATION2,POSITION_NAME,COMPANY,DEPARTMENT1,DEPARTMENT2,POSTAL,STREET1,TELL,FAX,MOBILE,EMAIL,INSERT_DATE,UPDATE_DATE,CHANGEDATE_DATE,STREET2,COMMENT1, rownum as rn FROM (SELECT * FROM CUSTOMER";// 準備//

			StringBuilder Sql = new StringBuilder(sql);
			if(form.getOrderby() == 0) {
				Sql.append(" ORDER BY USER_ID ASC) WHERE 1=1");
			}else {
				Sql.append(" ORDER BY USER_ID DESC) WHERE 1=1");
			}
			sql(form,Sql,1);
			// ドライバロード.DB接続.SQLをセット
			st = db.connect().prepareStatement(Sql.toString());
			int index = 0;
			st = setSql(form,st,index,1);
			rs = st.executeQuery();// 実行と結果の戻り//
			users = new ArrayList<>();
			while (rs.next()) {// 次のレコードに下がれればの条件式//
				UserBean user = new UserBean();
				user.setUserId(rs.getInt("USER_ID"));
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setFirstNameKana(rs.getString("FIRST_NAME_KANA"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setLastNameKana(rs.getString("LAST_NAME_KANA"));
				user.setTitle(rs.getString("TITLE"));
				user.setSex(rs.getString("SEX"));
				user.setClassification1(rs.getString("CLASSIFICATION1"));
				user.setClassification2(rs.getString("CLASSIFICATION2"));
				user.setCompany(rs.getString("COMPANY"));
				user.setDepartment1(rs.getString("DEPARTMENT1"));
				user.setDepartment2(rs.getString("DEPARTMENT2"));
				user.setPostal(rs.getString("POSTAL"));
				user.setStreet1(rs.getString("STREET1"));
				user.setFax(rs.getString("FAX"));
				user.setTell(rs.getString("TELL"));
				user.setMobile(rs.getString("MOBILE"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPositionName(rs.getString("POSITION_NAME"));
				user.setStreet2(rs.getString("STREET2"));
				user.setComment1(rs.getString("COMMENT1"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}
		return users;

	}

	public void sql(UserViewForm form,StringBuilder Sql,int limit) {
		if (form.getUserId() != 0) {
			Sql.append(" and USER_ID = ?");
		}
		if (form.getFirstName() != null && !form.getFirstName().isEmpty()) {
			Sql.append(" and FIRST_NAME like ?");
		}
		if (form.getLastName() != null && !form.getLastName().isEmpty()) {
			Sql.append(" and LAST_NAME like ?");
		}
		if (form.getFirstNameKana() != null && !form.getFirstNameKana().isEmpty()) {
			Sql.append(" and FIRST_NAME_KANA like ?");
		}
		if (form.getFirstNameKana() != null && !form.getFirstNameKana().isEmpty()) {
			Sql.append(" and LAST_NAME_KANA like ?");
		}
		if (form.getCompany() != null && !form.getCompany().isEmpty()) {
			Sql.append(" and COMPANY like ?");
		}
		if (form.getClassification1() != null && !form.getClassification1().isEmpty()) {
			Sql.append(" and CLASSIFICATION1 LIKE ?");
		}
		if (form.getClassification2() != null && !form.getClassification2().isEmpty()) {
			Sql.append(" and CLASSIFICATION2 like ?");
		}
		if (form.getPositionName() != null && !form.getPositionName().isEmpty()) {
			Sql.append(" and POSITION_NAME like ?");
		}
		if (form.getStreet1() != null && !form.getStreet1().isEmpty()) {
			Sql.append(" and STREET1 like ?");
		}
		if (form.getStreet2() != null && !form.getStreet2().isEmpty()) {
			Sql.append(" and STREET2 like ?");
		}
		if (form.getTell() != null && !form.getTell().isEmpty()) {
			Sql.append(" and TELL like ?");
		}
		if (form.getFax() != null && !form.getFax().isEmpty()) {
			Sql.append(" and FAX like ?");

		}
		if (form.getMobile() != null && !form.getMobile().isEmpty()) {
			Sql.append(" and MOBILE like ?");
		}
		if (form.getEmail() != null && !form.getEmail().isEmpty()) {
			Sql.append(" and EMAIL like ?");
		}
		if(limit == 1) {
			Sql.append(" )where rn between ? and ?");
		}
		}

	public PreparedStatement setSql(UserViewForm form,PreparedStatement st,int index,int limit) throws SQLException {

		if (form.getUserId() != 0) {
			st.setInt(++index, form.getUserId());
		}
		if (form.getFirstName() != null && !form.getFirstName().isEmpty()) {
			st.setString(++index, "%" + form.getFirstName() + "%");
		}
		if (form.getLastName() != null && !form.getLastName().isEmpty()) {
			st.setString(++index, "%" + form.getLastName() + "%");
		}
		if (form.getFirstNameKana() != null && !form.getFirstNameKana().isEmpty()) {
			st.setString(++index, "%" + form.getFirstNameKana() + "%");
		}
		if (form.getFirstNameKana() != null && !form.getFirstNameKana().isEmpty()) {
			st.setString(++index, "%" + form.getFirstNameKana() + "%");
		}
		if (form.getCompany() != null && !form.getCompany().isEmpty()) {
			st.setString(++index, "%" + form.getCompany() + "%");
		}
		if (form.getClassification1() != null && !form.getClassification1().isEmpty()) {
			st.setString(++index, "%" + form.getClassification1() + "%");
		}
		if (form.getClassification2() != null && !form.getClassification2().isEmpty()) {
			st.setString(++index, "%" + form.getClassification2() + "%");
		}
		if (form.getPositionName() != null && !form.getPositionName().isEmpty()) {
			st.setString(++index, "%" + form.getPositionName() + "%");
		}
		if (form.getStreet1() != null && !form.getStreet1().isEmpty()) {
			st.setString(++index, "%" + form.getStreet1() + "%");
		}
		if (form.getStreet2() != null && !form.getStreet2().isEmpty()) {
			st.setString(++index, "%" + form.getStreet2() + "%");
		}
		if (form.getTell() != null && !form.getTell().isEmpty()) {
			st.setString(++index, "%" + form.getTell() + "%");
		}
		if (form.getFax() != null && !form.getFax().isEmpty()) {
			st.setString(++index, "%" + form.getFax() + "%");
		}
		if (form.getMobile() != null && !form.getMobile().isEmpty()) {
			st.setString(++index, "%" + form.getMobile() + "%");
		}
		if (form.getEmail() != null && !form.getEmail().isEmpty()) {
			st.setString(++index, "%" + form.getEmail() + "%");
		}
		if(limit == 1) {
			st.setInt(++index,(form.getOffset()-1) * 10 + 1);
			st.setInt(++index,(form.getOffset()-1) * 10 + 10);
		}
		return st;
	}
	//Update
	public void doUpdate(UpdateForm form) {
		DBConnector db = new DBConnector();
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		try {
			String sql = "UPDATE CUSTOMER SET FIRST_NAME = ?, LAST_NAME = ?,FIRST_NAME_KANA = ?, LAST_NAME_KANA = ?, TITLE = ?, SEX = ?, CLASSIFICATION1 = ?, CLASSIFICATION2 = ?, POSITION_NAME = ?, COMPANY = ?, DEPARTMENT1 = ?, DEPARTMENT2 = ?, POSTAL = ?, STREET1 = ?,STREET2 = ?, TELL = ?, FAX = ?, MOBILE = ?, EMAIL = ?, UPDATE_DATE = SYSDATE, CHANGEDATE_DATE = TO_DATE(?), COMMENT1 = ? WHERE USER_ID = ?";
			StringBuilder Sql = new StringBuilder(sql);
			st = db.connect().prepareStatement(Sql.toString());// 準備//
			int index = 0;
			st.setString(++index, form.getFirstName());
			st.setString(++index, form.getLastName());
			st.setString(++index, form.getFirstNameKana());
			st.setString(++index, form.getLastNameKana());
			st.setString(++index, form.getTitle());
			st.setString(++index, form.getSex());
			st.setString(++index, form.getClassification1());
			st.setString(++index, form.getClassification2());
			st.setString(++index, form.getPositionName());
			st.setString(++index, form.getCompany());
			st.setString(++index, form.getDepartment1());
			st.setString(++index, form.getDepartment2());
			st.setString(++index, form.getPostal());
			st.setString(++index, form.getStreet1());
			st.setString(++index, form.getStreet2());
			st.setString(++index, form.getTell());
			st.setString(++index, form.getFax());
			st.setString(++index, form.getMobile());
			st.setString(++index, form.getEmail());
			st.setDate(++index, form.getChangeDate());
			st.setString(++index, form.getComment1());
			st.setInt(++index, form.getUserId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close(st);
		}

	}

	//Oneレコード分データ
	public List<UserBean> getOneRecode(int userId) {
		DBConnector db = new DBConnector();
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		List<UserBean> users = null;
		ResultSet rs = null;
		try {
			st = db.connect().prepareStatement("SELECT * FROM CUSTOMER WHERE USER_ID = ?");
			st.setInt(1, userId);
			rs = st.executeQuery();// 実行と結果の戻り//
			users = new ArrayList<>();
			UserBean user = new UserBean();
			while (rs.next()) {// 次のレコードに下がれればの条件式//
				user.setUserId(rs.getInt("USER_ID"));
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setFirstNameKana(rs.getString("FIRST_NAME_KANA"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setLastNameKana(rs.getString("LAST_NAME_KANA"));
				user.setTitle(rs.getString("TITLE"));
				user.setSex(rs.getString("SEX"));
				user.setClassification1(rs.getString("CLASSIFICATION1"));
				user.setClassification2(rs.getString("CLASSIFICATION2"));
				user.setCompany(rs.getString("COMPANY"));
				user.setDepartment1(rs.getString("DEPARTMENT1"));
				user.setDepartment2(rs.getString("DEPARTMENT2"));
				user.setPostal(rs.getString("POSTAL"));
				user.setStreet1(rs.getString("STREET1"));
				user.setFax(rs.getString("FAX"));
				user.setTell(rs.getString("TELL"));
				user.setMobile(rs.getString("MOBILE"));
				user.setEmail(rs.getString("EMAIL"));
				user.setStreet2(rs.getString("STREET2"));
				user.setComment1(rs.getString("COMMENT1"));
				user.setPositionName(rs.getString("POSITION_NAME"));
				user.setChangeDate(rs.getDate("CHANGEDATE_DATE"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}
		return users;
	}

	//プールダウン用 敬称リスト
	public List<UserBean> doPullDownTitle() {
		DBConnector db = new DBConnector();
		List<UserBean> pullDowns = null;
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		ResultSet rs = null;
		try {
			pullDowns = new ArrayList<UserBean>();
			st = db.connect().prepareStatement("SELECT TITLE_NAME FROM PULLDOWN_TITLE");
			rs = st.executeQuery();
			while (rs.next()) {
				UserBean list = new UserBean();
				list.setTitle(rs.getString("TITLE_NAME"));
				pullDowns.add(list);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}

		return pullDowns;

	}

	//プールダウン用 性別リスト
	public List<UserBean> doPullDownSex() {
		DBConnector db = new DBConnector();
		List<UserBean> pullDowns = null;
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		ResultSet rs = null;
		try {
			pullDowns = new ArrayList<UserBean>();
			st = db.connect().prepareStatement("SELECT SEX_NAME FROM PULLDOWN_SEX");
			rs = st.executeQuery();
			while (rs.next()) {
				UserBean list = new UserBean();
				list.setSex(rs.getString("SEX_NAME"));
				pullDowns.add(list);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}

		return pullDowns;

	}

	//プールダウン用 分類１リスト
	public List<UserBean> doPullDownClassification1() {
		DBConnector db = new DBConnector();
		List<UserBean> pullDowns = null;
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		ResultSet rs = null;
		try {
			pullDowns = new ArrayList<UserBean>();
			st = db.connect().prepareStatement("SELECT CLASSIFICATION1_NAME FROM PULLDOWN_CLASSIFICATION1");
			rs = st.executeQuery();
			while (rs.next()) {
				UserBean list = new UserBean();
				list.setClassification1(rs.getString("CLASSIFICATION1_NAME"));
				pullDowns.add(list);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}

		return pullDowns;

	}

	//プールダウン用 分類2リスト
	public List<UserBean> doPullDownClassification2() {
		DBConnector db = new DBConnector();
		List<UserBean> pullDowns = null;
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		ResultSet rs = null;
		try {
			pullDowns = new ArrayList<UserBean>();
			st = db.connect().prepareStatement("SELECT CLASSIFICATION2_NAME FROM PULLDOWN_CLASSIFICATION2");
			rs = st.executeQuery();
			while (rs.next()) {
				UserBean list = new UserBean();
				list.setClassification2(rs.getString("CLASSIFICATION2_NAME"));
				pullDowns.add(list);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}

		return pullDowns;

	}


	//PullDown 敬称追加書き込み
	public void titleInsert(String titleName) {
		DBConnector db = new DBConnector();
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		try {
			st = db.connect().prepareStatement("INSERT INTO PULLDOWN_TITLE VALUES(?)");
			st.setString(1, titleName);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			db.close(st);
		}
	}

	//PullDown 分類1追加書き込み
	public void classInsert1(String classification1) {
		DBConnector db = new DBConnector();
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		try {
			st = db.connect().prepareStatement("INSERT INTO PULLDOWN_CLASSIFICATION1 VALUES(?)");
			st.setString(1, classification1);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			db.close(st);
		}
	}

	//PullDown 分類2追加書き込み
	public void classInsert2(String classification2) {
		DBConnector db = new DBConnector();
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		try {
			st = db.connect().prepareStatement("INSERT INTO PULLDOWN_CLASSIFICATION2 VALUES(?)");
			st.setString(1, classification2);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			db.close(st);
		}
	}

	//PullDown 敬称削除
		public void titleDelete(String titleName) {
			DBConnector db = new DBConnector();
			PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
			try {
				st = db.connect().prepareStatement("DELETE FROM PULLDOWN_TITLE WHERE TITLE_NAME = ?");
				st.setString(1, titleName);
				st.executeUpdate();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} finally {
				db.close(st);
			}
		}

		//PullDown 分類1削除
		public void classDelete1(String classification1) {
			DBConnector db = new DBConnector();
			PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
			try {
				st = db.connect().prepareStatement("DELETE FROM PULLDOWN_CLASSIFICATION1 WHERE CLASSIFICATION1_NAME = ?");
				st.setString(1, classification1);
				st.executeUpdate();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} finally {
				db.close(st);
			}
		}

		//PullDown 分類2削除
		public void classDelete2(String classification2) {
			DBConnector db = new DBConnector();
			PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
			try {
				st = db.connect().prepareStatement("DELETE FROM PULLDOWN_CLASSIFICATION2 WEHRE CLASSIFICATION2_NAME = ?");
				st.setString(1, classification2);
				st.executeUpdate();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} finally {
				db.close(st);
			}
		}

	//顧客データ削除用
	public void doDelete(DeleteForm form) throws SQLException {
		DBConnector db = new DBConnector();
		PreparedStatement st = null;
		st = db.connect().prepareStatement("DELETE FROM CUSTOMER WHERE USER_ID = ?");
		try {
			st.setInt(1, form.getUserId());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		st.executeUpdate();

	}

	//新規顧客登録
	public void doInsert(NewForm form) {
		DBConnector db = new DBConnector();
		PreparedStatement st = null;
		//		int i = doUserIdMax();
		try {
			int index = 0;
			st = db.connect().prepareStatement(
					"INSERT INTO CUSTOMER VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, SYSDATE,SYSDATE,TO_DATE(?),?,?)");
			st.setInt(++index, doUserIdMax());
			st.setString(++index, form.getFirstName());
			st.setString(++index, form.getLastName());
			st.setString(++index, form.getFirstNameKana());
			st.setString(++index, form.getLastNameKana());
			st.setString(++index, form.getTitle());
			st.setString(++index, form.getSex());
			st.setString(++index, form.getClassification1());
			st.setString(++index, form.getClassification2());
			st.setString(++index, form.getPositionName());
			st.setString(++index, form.getCompany());
			st.setString(++index, form.getDepartment1());
			st.setString(++index, form.getDepartment2());
			st.setString(++index, form.getPostal());
			st.setString(++index, form.getStreet1());
			st.setString(++index, form.getTell());
			st.setString(++index, form.getFax());
			st.setString(++index, form.getMobile());
			st.setString(++index, form.getEmail());
			st.setDate(++index, form.getChangeDate());
			st.setString(++index, form.getStreet2());
			st.setString(++index, form.getComment1());
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			db.close(st);
		}
	}

	//USER_IDの最大値
	public int doUserIdMax() {
		DBConnector db = new DBConnector();
		PreparedStatement st = null;
		ResultSet rs = null;
		int maxId = 0;
		try {
			st = db.connect().prepareStatement("SELECT MAX(USER_ID) FROM CUSTOMER");
			rs = st.executeQuery();
			while (rs.next()) {// 次のレコードに下がれればの条件式//
				maxId = rs.getInt("MAX(USER_ID)");
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			db.close(st);
		}
		return ++maxId;

	}

	// ページ送り
		public int [] getPage(UserViewForm form) {
			DBConnector db = new DBConnector();
			PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
			ResultSet rs = null;
			int max = 10;
			int record = 0;
			int page = 0;
			int button[] = null;
			try {
				String sql = "SELECT count(*) AS record FROM customer WHERE 1=1";
						// ドライバロード.DB接続.SQLをセット

				StringBuilder Sql = new StringBuilder(sql);

				sql(form,Sql,0);
						// ドライバロード.DB接続.SQLをセット
				st = db.connect().prepareStatement(Sql.toString());

				int index = 0;

				st = setSql(form,st,index,0);
				rs = st.executeQuery();// 実行と結果の戻り//
				if(rs.next()) {
					record = rs.getInt("record");								//レコード数取得
				}
				page = (record / max) + (record % 10 == 0 ? 0 : 1);

				button = new int [page];
				for(int i = 0; i < button.length; i++) {
					button[i] = i + 1;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close(st, rs);
			}
			return button;
		}

}